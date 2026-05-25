package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import common.Common;
import model.GroupData;

import java.util.ArrayList;

public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-c"})
    int count;


    public static void main(String[] args) {
      var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
      generator.run();
    }

    private void run() {
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

    private Object generatorGroups() {
        var result = new ArrayList<GroupData>();
        for (int i=0;i<count;i++) {
            result.add(new GroupData()
                    .withName(Common.randomString(i*10))
                    .withHeader(Common.randomString(i*10))
                    .withFooter(Common.randomString(i*10)));
        }
        return result;
    }

    private Object generatorContacts() {
        return null;
    }

    private void save(Object data) {
    }

}
