package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.openGroupsPage();
        app.createGroup(new GroupData("name", "group header", "group footer"));
    }


    @Test
    public void canCreateGroupWithEmptyName() {
        app.openGroupsPage();
        app.createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.openGroupsPage();
        var emptyGroup =new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        app.createGroup(groupWithName);
    }

    @Test
    public void canCreateGroupWithHeaderOnly() {
        app.openGroupsPage();
        app.createGroup(new GroupData().withHeader("some header"));
    }

    @Test
    public void canCreateGroupWithFooterOnly() {
        app.openGroupsPage();
        app.createGroup(new GroupData().withFooter("some footer"));
    }

}
