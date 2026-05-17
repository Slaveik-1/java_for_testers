package tests.Group;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>();
        for (var name :List.of("","group name")){
            for (var header:List.of("","group header")){
                for (var footer:List.of("","group footer")){
                    result.add(new GroupData()
                            .withName(name)
                            .withHeader(header)
                            .withFooter(footer));
                }
            }
        }
        for (int i=0;i<5;i++) {
            result.add(new GroupData()
                    .withName(randomString(i*10))
                    .withHeader(randomString(i*10))
                    .withFooter(randomString(i*10)));
        }
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name ' ","group header", "group footer")));
        return result;
    }

//    @Test
//    public void canCreateGroup() {
//        app.groupHelper().createGroup(new GroupData("group name", "group header", "group footer"));
//    }


//    @ParameterizedTest
//    @ValueSource(strings = {"group name","group name'"})
//    public void canCreateGroupWithEmptyName(String name) {
//        int groupCount = app.groupHelper().getCount();
//        app.groupHelper().createGroup(new GroupData(name, "group header", "group footer"));
//        int newGroupCount = app.groupHelper().getCount();
//        Assertions.assertEquals(groupCount+1,newGroupCount);
//    }

//    @Test
//    public void canCreateGroupWithNameOnly() {
//        var emptyGroup =new GroupData();
//        var groupWithName = emptyGroup.withName("some name");
//        app.groupHelper().createGroup(groupWithName);
//    }

//    @Test
//    public void canCreateGroupWithHeaderOnly() {
//        app.groupHelper().createGroup(new GroupData().withHeader("some header"));
//    }
//
//    @Test
//    public void canCreateGroupWithFooterOnly() {
//        app.groupHelper().createGroup(new GroupData().withFooter("some footer"));
//    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData name) {
        int groupCount = app.groupHelper().getCount();
            app.groupHelper().createGroup(name);
        int newGroupCount = app.groupHelper().getCount();
        Assertions.assertEquals(groupCount+1,newGroupCount);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData name) {
        int groupCount = app.groupHelper().getCount();
        app.groupHelper().createGroup(name);
        int newGroupCount = app.groupHelper().getCount();
        Assertions.assertEquals(groupCount,newGroupCount);
    }

}
