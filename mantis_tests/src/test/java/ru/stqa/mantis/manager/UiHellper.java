package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class UiHellper extends HelperBase{

    public UiHellper(ApplicationManager manager) {
        super(manager);
    }


    public void registrationUser(String Username, String Email){
        click(By.linkText("Signup for a new account"));
        type(By.name("username"), Username);
        type(By.name("email"), Email);
        click(By.xpath("//input[@value='Signup']"));
        click(By.xpath("//a[normalize-space(text()) = 'Proceed']"));
    }

    public void finish(String url,String user, String pass){
        manager.driver().get(url);
        type(By.id("realname"), user);
        type(By.id("password"), pass);
        type(By.id("password-confirm"), pass);
        click(By.cssSelector("button.btn-success"));
    }

}



