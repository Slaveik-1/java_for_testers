
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupRemovalTest extends TestBase {

    @AfterEach
    public void tearDown() {
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }

    @Test
    public void canRemoveGroup()  {
        openGroupsPage();
        if(!isGroupPresent()){
            createGroup("group name","header","footer");
        }
        removeGroup();
    }

}
