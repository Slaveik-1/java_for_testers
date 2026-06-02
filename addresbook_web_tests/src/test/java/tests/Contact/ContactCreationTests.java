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
import java.util.Random;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();

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

//    @Test
//    public void canCreateFull(){
//        app.contactHelper().createContact(new ContactData("", "Пипяу","Пипяуович","Пипяуов","Pipyau", "", "Title","OOO Pipyau",
//                "The Pentagon, Washington, DC 20301-0003","dom","123","work","e1",
//                "e2","e3","home"));
//    }

    @ParameterizedTest
    @MethodSource("contactProvider")
        public void canCreateMultipleContactFIO(ContactData contact){
        var oldContact = app.hbm().getContactList();
        app.contactHelper().createContact(contact);
        var newContact = app.hbm().getContactList();
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
                /* .withPhoto(randomFile("src/test/resources/images"))*/;
        if(app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group name", "header", "footer"));
        }

        //первая группа всегда - none , буду брать последнюю через костыль (был выбор между stream и кривой костыль я выбрал 2ое
        var maxIndex = app.hbm().getGroupList().size();
        //Для дебага var maxIndex1 = app.hbm().getGroupList();
        var group = app.hbm().getGroupList().get(maxIndex-1);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contactHelper().create(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);

        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.withId(newRelated.get(newRelated.size() - 1).id()));
        expectedList.sort(compareById);


        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        Assertions.assertEquals(newRelated, expectedList);
    }
    @Test
    public void addContactToGroup(){
        app.contactHelper().a();
        if (app.contactHelper().checkboxes().isEmpty()){
            app.hbm().createContact(new ContactData()
                    .withNames(Common.randomString(10),
                            Common.randomString(10)));
        }
        //Есть ли контакт
//        if (app.hbm().getContactCount() == 0){
//            app.hbm().createContact(new ContactData()
//                    .withNames(Common.randomString(10),
//                            Common.randomString(10)));
//        }

        //Есть ли группа
        if(app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group name", "header", "footer"));
        }

//        // выбираем случайный контакт по бд
//        var allContactList = app.hbm().getContactList();
//        var rnd = new Random();
//        var indexContact = rnd.nextInt(allContactList.size());
//        var contact = allContactList.get(indexContact); //Жертва

        // выбираем случайный контакт
        // Позже вынесу в методы
        var allContactList = app.hbm().getContactList();
        var listC = app.contactHelper().getNonGroupList();
        var rnd = new Random();
        var indexContact = rnd.nextInt(listC.size());
        var trycontact = listC.get(indexContact);
//        var contact = allContactList.get(Integer.parseInt(trycontact));
        var contact = allContactList.stream()
                .filter(c -> c.id().equals(trycontact))
                .findFirst()
                .orElse(null);



        var maxIndex = app.hbm().getGroupList().size();
        //Выбор последней группы
        var group = app.hbm().getGroupList().get(maxIndex-1); //жертва

        //список контактов в группе до
        var oldRelated = app.hbm().getContactsInGroup(group);

        //добавление в группу
        app.contactHelper().addToGroupContact(contact, group);

        //список контактов в группе после
        var newRelated = app.hbm().getContactsInGroup(group);



        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);

        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact);
        expectedList.sort(compareById);

        Assertions.assertEquals(newRelated, expectedList);
    }

//    @Test
//    public void addContactToGroup1(){
//        List a = app.contactHelper().getNonGroupList();
//    }

    //нет клика на чекбокс и нет клика на группу

    @Test
    public void deletedContactFromGroup() {

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withNames(Common.randomString(10),
                            Common.randomString(10)));
        }

        //Есть ли группа
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "header", "footer"));
        }

        var rnd = new Random();
        var groupList = app.hbm().getGroupList();
        var indexGroup = rnd.nextInt(groupList.size());
        var group = groupList.get(indexGroup);

        var oldContacts = app.hbm().getContactsInGroup(group);

        if (oldContacts.isEmpty()) {
            if (app.hbm().getContactCount() == 0) {
                app.hbm().createContact(new ContactData()
                        .withNames(Common.randomString(10),
                                Common.randomString(10)));
            }

            var contactList = app.hbm().getContactList();
            var indexContact = rnd.nextInt(contactList.size());
            var contact = contactList.get(indexContact);

            app.contactHelper().addToGroupContact(contact, group);

            oldContacts = app.hbm().getContactsInGroup(group);
        }
        var contact = oldContacts.get(rnd.nextInt(oldContacts.size()));
        app.contactHelper().RemoveGroupFromContact(contact, group);

        var newContacts = app.hbm().getContactsInGroup(group);

        Comparator<ContactData> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(contact);
        expectedList.sort(compareById);


        Assertions.assertEquals(newContacts, expectedList);

    }

}
