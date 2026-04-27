package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.geometry.figures.Square;

public class SquareTests {

    @Test
    void canCalculateArea() {
        var s = new Square(5.0);
        var result = s.Area();
        Assertions.assertEquals(25.0,result,"Ну не получилось");
//        if (result!=25.0){
//            throw new AssertionError(String.format("Expected %f, actual %f", 25.0,result));
//        }

    }

    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(20.0,new Square(5.0).perimeter());
    }

    @Test
    void cannotCreateSquareWithNegativeSide(){
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }


}
