package tests;

import manager.ApplicationManager;
import model.GroupData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupRemovalTest extends TestBase {

    @AfterEach
    public void tearDown() {
        ApplicationManager.driver.findElement(By.linkText("Logout")).click();
        ApplicationManager.driver.quit();
    }

    @Test
    public void canRemoveGroup()  {
        app.openGroupsPage();
        if(!app.isGroupPresent()){
            app.createGroup(new GroupData("group name", "header", "footer"));
        }
        app.removeGroup();
    }

}
