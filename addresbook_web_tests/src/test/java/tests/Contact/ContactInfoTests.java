package tests.Contact;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones(){
        if (!app.contactHelper().isContactPresent()){
            var emptyContact = new ContactData();
            var contactFIO = emptyContact
                    .withFIO("Пипяу","Пипяуович","Пипяуов")
                    .withHome("123")
                    .withMobile("456")
                    .withWork("789");
            app.contactHelper().createContact(contactFIO);
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contactHelper().getPhones(contact);
        var expected = Stream.of(contact.Home(),contact.Mobile(),contact.Work())
                .filter(s-> s!=null &&!"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,phones);
    }

}
