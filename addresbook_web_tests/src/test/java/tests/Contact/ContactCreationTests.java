package tests.Contact;

import common.Common;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var Firstname :List.of("","Пипяу")) {
//            //    for (var Middlename : List.of("", "Пипяуович")) {
//                for (var Lastname : List.of("", "Пипяуов")) {
//                    result.add(new ContactData().withNames(Firstname,/*Middlename,*/Lastname));
//                }
//         //   }
//        }
        var json = "";
        try (var reader = new FileReader("contacts.json");
             var breader = new BufferedReader(reader)
        ) {
            var line =  breader.readLine();
            while (line!= null){
                json=json+line;
                line=breader.readLine();
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json,  new TypeReference<List<ContactData>>(){});
        result.addAll(value);
        return result;
    }

    @Test
    public void canCreateContactFIO(){
        var emptyContact = new ContactData()
                .withFirstname("Пипяу")
                .withLastname("Пипяуов")
                .withPhoto(randomFile("src/test/resources/images"));
        app.contactHelper().createContact(emptyContact);
    }

    @Test
    public void canCreateFull(){
        app.contactHelper().createContact(new ContactData("", "Пипяу","Пипяуович","Пипяуов","Pipyau", "", "Title","OOO Pipyau",
                "The Pentagon, Washington, DC 20301-0003","dom","123","work","e1",
                "e2","e3","home"));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
        public void canCreateMultipleContactFIO(ContactData contact){
        var oldContact = app.contactHelper().getList();
        app.contactHelper().createContact(contact);
        var newContact = app.contactHelper().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        var expectedList = new ArrayList<>(oldContact);
        expectedList.add(contact.withId(newContact.get(newContact.size()-1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact, expectedList);
    }

    @Test
    public void canCreateContactInGroup(){
        var contact = new ContactData()
                .withFirstname(Common.randomString(10))
                .withLastname(Common.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if(app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group name", "header", "footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contactHelper().create(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size()+1,newRelated.size());
    }

}
