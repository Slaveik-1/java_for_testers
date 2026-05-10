package manager;

import org.openqa.selenium.By;

public class LoginHelper
{

    private final ApplicationManager manager;

    public LoginHelper(ApplicationManager manager){
        this.manager = manager;
    }


    void login(String name, String pass) {
        manager.driver.findElement(By.name("user")).sendKeys(name);
        manager.driver.findElement(By.name("pass")).click();
        manager.driver.findElement(By.name("pass")).sendKeys(pass);
        manager.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }
}
