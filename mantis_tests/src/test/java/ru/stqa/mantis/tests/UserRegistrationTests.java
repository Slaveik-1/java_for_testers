package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.Common;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {


    //  void canRegisterUser(){
    //var email = String.format("%s@localhost", username);
//          // создать пользователя на почтовом сервере (james)
//          // открыть браузер и заполнить форму создания, отправить форму (браузер)
//          // получаем(ожидаем) письмо на почту ? (mail)
//          // извлекаем ссылку из письма
//          // в браузере проходим по ссылке. завершаем регистрацию ?? (браузер)
//          // проверяем что пользователь может залогиниться (http)

    @Test
    void canRegisterUser() {
        var user = Common.randomString(9);
        var email = (String.format("%s@localhost", Common.randomString(7)));
        var pass = app.getProperties("pass");

        try {
            app.jamesCli().addUser(email, pass);
            app.http().registrationUser(user, email);
            var message = app.mail().receive(email, pass, Duration.ofSeconds(25));
            Assertions.assertFalse(message.isEmpty(), "No message");
            System.out.println(message);

            var url = app.mail().getUrl(message);
            System.out.println(url);

            app.http().finish(url, user, pass);

            app.http().login(user, pass);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
