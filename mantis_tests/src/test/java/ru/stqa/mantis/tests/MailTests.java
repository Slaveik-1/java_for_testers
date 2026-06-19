package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MailTests extends TestBase{

    @Test
    void canReceiveEmail(){
        var message = app.mail().receive("user1@localhost","password");
        Assertions.assertEquals(1, message.size());

    }

}
