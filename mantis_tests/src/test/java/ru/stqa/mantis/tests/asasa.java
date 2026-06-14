package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.Common;

public class asasa extends TestBase {

    @Test
    void canCreateUser() throws InterruptedException {
        app.jamesCli().addUser(String.format("%s@localhost", Common.randomString(7)),"password");
    }

}
