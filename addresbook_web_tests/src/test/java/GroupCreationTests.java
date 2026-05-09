import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup(new GroupData("name", "group header", "group footer"));
    }


    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        openGroupsPage();
        var emptyGroup =new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);
    }

    @Test
    public void canCreateGroupWithHeaderOnly() {
        openGroupsPage();
        createGroup(new GroupData().withHeader("some header"));
    }

    @Test
    public void canCreateGroupWithFooterOnly() {
        openGroupsPage();
        createGroup(new GroupData().withFooter("some footer"));
    }

}
