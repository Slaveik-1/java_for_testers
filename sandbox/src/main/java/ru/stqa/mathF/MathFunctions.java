package ru.stqa.mathF;
//Спрятал формулы для треугольника
public class MathFunctions {
    public static double getSemiperimeter(double a, double b, double c){
        return ((a+b+c)/2);
    }
    public static double getGeronSquare(double a, double b, double c){
        double p = getSemiperimeter(a,b,c);
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}
