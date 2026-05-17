package tests.Contact;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider(){
        var result = new ArrayList<ContactData>();
        for (var Firstname :List.of("","Пипяу")) {
            for (var Middlename : List.of("", "Пипяуович")) {
                for (var Lastname : List.of("", "Пипяуов")) {
                    result.add(new ContactData().withFIO(Firstname,Middlename,Lastname));
                }
            }
        }
        for (int i=0;i<5;i++) {
            result.add(new ContactData().withFIO(randomString(i*10),randomString(i*10),randomString(i*10)));
        }
        return result;
    }

    @Test
    public void canCreateContactFIO(){
        var emptyContact = new ContactData();
        var contactFIO = emptyContact.withFIO("Пипяу","Пипяуович","Пипяуов");
        app.contactHelper().createContact(contactFIO);
    }

    @Test
    public void canCreateFull(){
        app.contactHelper().createContact(new ContactData("Пипяу","Пипяуович","Пипяуов","Pipyau","Title","OOO Pipyau",
                "The Pentagon, Washington, DC 20301-0003","dom","123","work","e1",
                "e2","e3","home"));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
        public void canCreateMultipleContactFIO(ContactData contact){
        int contactCount = app.contactHelper().getContactCount();
        app.contactHelper().createContact(contact);
        int newContact = app.contactHelper().getContactCount();
        Assertions.assertEquals(contactCount+1,newContact);
    }
}
