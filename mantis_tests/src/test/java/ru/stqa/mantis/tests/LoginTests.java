package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    //Вход админа
    @Test
    void canLogin(){
        app.http().login("administrator","root");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

//    @Test
//    void canLoginUser(){
//        app.http().login("administrator","password");
//        Assertions.assertTrue(app.http().isLoggedIn());
//    }
}


