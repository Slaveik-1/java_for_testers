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

    }

    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(20.0,new Square(5.0).perimeter());
    }
}
