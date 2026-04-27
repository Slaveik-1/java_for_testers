package ru.stqa.geometry.figures;

public record Rectangle(double a, double b) {

    public Rectangle {
        if ((a<0)|(b<0)){
            throw new IllegalArgumentException("Rectangle ne noll pj");
        }
    }


    public static void printRectangleArea(double a, double b) {
        var text =String.format("Площадь прямоугольника со сторонами со сторонами %f и %f = %f", a,b,printRectangleeArea(a,b));
        System.out.println(text);
    }

    public static double printRectangleeArea(double a, double b) {
        return (a * b);
    }
}

