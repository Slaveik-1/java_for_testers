package ru.stqa.geometry.figures;

import ru.stqa.mathF.MathFunctions;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if ((a<0|b<0|c<0)){
            throw new IllegalArgumentException("Triangle cannot have a side with a negative value.");
        }
        if ((a+b<c|a+c<b|b+c<a)){
            throw new IllegalArgumentException("One side cannot be greater than the sum of the other two.");
        }
    }
//Запрос площади
    public double getArea() {
        return MathFunctions.getGeronSquare(a,b,c);
    }
//Запрос периметра
    public double getPerimeter() {
        return this.a+this.b+this.c;
    }
}
