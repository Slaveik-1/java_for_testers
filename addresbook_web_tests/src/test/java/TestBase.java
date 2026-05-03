import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
    protected WebDriver driver;


    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new FirefoxDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1936, 1048));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).click();
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }
    }

    protected void createGroup(String newGroup, String group_header, String group_footer) {
        driver.findElement(By.cssSelector("form:nth-child(2)")).click();
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(newGroup);
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group_header);
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group_footer);
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    protected void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    protected boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    protected void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.xpath("(.//input[@name='delete'])[1]")).click();
        driver.findElement(By.linkText("group page")).click();
    }
}
