package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.Common;
import model.ContactData;
import model.GroupData;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-c"})
    int count;


    public static void main(String[] args) throws IOException {
      var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
      generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)){
            return generatorGroups();
        } else if ("contacts".equals(type)) {
            return generatorContacts();
        } else {
            throw new IllegalArgumentException("Мы такое не знаем" + type);
        }
    }

    private Object generateData(Supplier<Object> dataSupplier) {
        Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
        var result = new ArrayList<Object>();
        for (int i=0;i<count;i++) {
            result.add(dataSupplier.get());
        }
        return result;
    }

    private Object generatorGroups() {
        return generateData(()-> new GroupData()
                .withName(Common.randomString(10))
                .withHeader(Common.randomString(10))
                .withFooter(Common.randomString(10)));
    }

    private Object generatorContacts() {
        return generateData(()->new ContactData()
                        .withFirstname(Common.randomString(10))
                        .withLastname(Common.randomString(10)));

    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
            //mapper.writeValue(new File(output), data);
           var json =  mapper.writeValueAsString(data);

            try(var writer = new FileWriter(output);){
                writer.write(json);
            }
//            writer.close();
        }else if ("yaml".equals(format)){
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output),data);
        }else if ("xml".equals(format)){
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output),data);
        }
        else{
            throw new IllegalArgumentException("Неизвестный формат" + format);
        }
    }
}
