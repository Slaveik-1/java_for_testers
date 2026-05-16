package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTests(){
        //var array = new String[3];
        //array[0]="a";

        var array = new String[]{"a","b","c"};
        Assertions.assertEquals("a",array[0]);
        Assertions.assertEquals(3,array.length);

        array[0]="d";
        Assertions.assertEquals("d",array[0]);
    }

    @Test
    void listTests(){
        var list = new ArrayList<String>();
        Assertions.assertEquals(0,list.size());

        list.add("a");
        list.add("b");
        list.add("c");

        Assertions.assertEquals(3,list.size());
        Assertions.assertEquals("a",list.get(0));

        list.set(0,"d");
        Assertions.assertEquals("d",list.get(0));

        var listTwo = List.of("a","b","c");
        Assertions.assertEquals(3,listTwo.size());

        var listThree = new ArrayList<String>(List.of("a","b","c"));
        Assertions.assertEquals(3,listThree.size());
    }

}
