package tests.Contact;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactCreationTests extends TestBase {

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
}
