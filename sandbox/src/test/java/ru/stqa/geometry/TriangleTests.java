package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.stqa.geometry.figures.Triangle;

public class TriangleTests {

    @ParameterizedTest //Тест на площади
    @CsvSource({
            "3, 4, 5, 6", // просто красивый...
            "13, 13, 10, 60", // Равнобедренный
            "3, 3, 3, 3.9" //Равносторонний
    })
        void canCalculateGetArea(double a, double b, double c, double r){
        var t = new Triangle(a,b,c);
        System.out.println("Площадь треугольника "+t.getArea());
        Assertions.assertEquals(r,t.getArea(), 0.01,"Площадь не равна");
    }

    @ParameterizedTest //Тест на периметры
    @CsvSource({
            "3, 4, 5, 12", // просто красивый...
            "13, 13, 10, 36",// Равнобедренный
            "3, 3, 3, 9" //Равносторонний
    })
    void canCalculateGetPerimeter(double a, double b, double c, double r){
        var t = new Triangle(a,b,c);
        System.out.println("Периметр треугольника "+t.getPerimeter());
        Assertions.assertEquals(r,t.getPerimeter(), 0.01,"Периметр не равен");
    }
}
