package ru.stqa.geometry.figures;

public class Rectangle {

    public static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольника со сторонами " + a + " и "+ b + " = " + printRectangleeArea(a, b));
    }

    private static double printRectangleeArea(double a, double b) {
        return (a * b);
    }
}
