package ru.stqa.geometry.figures;

public class Rectangle {

    public static void printRectangleArea(double a, double b) {
        var text =String.format("Площадь прямоугольника со сторонами со сторонами %f и %f = %f", a,b,printRectangleeArea(a,b));
        System.out.println(text);
    }

    private static double printRectangleeArea(double a, double b) {
        return (a * b);
    }
}
