package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {

    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groupHelper;

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session ;
    }

    public GroupHelper groupHelper(){
        if (groupHelper == null){
            groupHelper = new GroupHelper(this);
        }
        return groupHelper;
    }


    public void init() {
        if (driver == null) {
            driver = new FirefoxDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1936, 1048));
            session().login("admin", "secret");
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
