public class Geometry {
    public static void main(String[] args) {
        //Лекция 1.3 Переменные и значения Лекция 1.4 Функции
        var integer_1 = 5d;
        System.out.println("Площадь квадрата со стороной " + integer_1 + " = " +(integer_1*integer_1));
        printSquareArea(6);
        printRectangleArea(3.0,5.0);
    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольника со сторонами " + a + " и "+ b + " = " + printRectangleeArea(a, b));
    }

    private static double printRectangleeArea(double a, double b) {
        return (a * b);
    }

    public static void printSquareArea(double side){
        System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
    }

    private static double squareArea(double side) {
        return side * side;
    }
}
