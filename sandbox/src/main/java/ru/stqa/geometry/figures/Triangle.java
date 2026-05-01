package ru.stqa.geometry.figures;

import ru.stqa.mathF.MathFunctions;

import java.util.Arrays;
import java.util.Objects;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if ((a<0||b<0||c<0)){
            throw new IllegalArgumentException("Triangle cannot have a side with a negative value.");
        }
        if ((a+b<c||a+c<b||b+c<a)){
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;

        double[] sides1 = {this.a, this.b, this.c};
        double[] sides2 = {triangle.a, triangle.b, triangle.c};

        Arrays.sort(sides1);
        Arrays.sort(sides2);

        return Double.compare(sides1[0], sides2[0]) == 0 &&
                Double.compare(sides1[1], sides2[1]) == 0 &&
                Double.compare(sides1[2], sides2[2]) == 0;

    }

    //По аналогии с лекцией 2.4
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Triangle triangle = (Triangle) o;
//        return
//                (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)||
//                        (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)||
//                        (Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0)||
//                        (Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)||
//                        (Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)||
//                        (Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
