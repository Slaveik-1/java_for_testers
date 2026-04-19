package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        //Лекция 1.3 Переменные и значения Лекция 1.4 Функции
        var integer_1 = 5d;
        System.out.println("Площадь квадрата со стороной " + integer_1 + " = " +(integer_1*integer_1));

        Square.printSquareArea(6);

        Rectangle.printRectangleArea(3.0,5.0);
    }

}
