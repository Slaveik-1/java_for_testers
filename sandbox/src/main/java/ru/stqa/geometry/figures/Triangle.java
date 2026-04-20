package ru.stqa.geometry.figures;

import ru.stqa.mathF.MathFunctions;

public record Triangle(double a, double b, double c) {
//Запрос площади
    public double getArea() {
        return MathFunctions.getGeronSquare(a,b,c);
    }
//Запрос периметра
    public double getPerimeter() {
        return this.a+this.b+this.c;
    }
}
