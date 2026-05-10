package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTest extends TestBase {


    @Test
    public void canRemoveGroup()  {
        if(!app.groupHelper().isGroupPresent()){
            app.groupHelper().createGroup(new GroupData("group name", "header", "footer"));
        }
        app.groupHelper().removeGroup();
    }

}
