package ru.stqa.geometry.figures;

public record Square(double side) {


    public static void printSquareArea(Square s){
        System.out.println("Площадь квадрата со стороной " + s.side + " = " + s.Area());
        System.out.println(String.format(
                "Площадь квадрата со стороной %f = %f", s.side, s.Area()
        ));
    }


    public double Area() {
    return this.side*this.side;
    }

    public double perimeter() {
        return 4*this.side;
    }
}
