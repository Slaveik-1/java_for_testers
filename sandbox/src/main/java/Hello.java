import java.io.File;

public class Hello {
    private static String Hello = "Hello, world!";

    public static void main(String[] args) {

        try {
            var z = calculate();
            System.out.println(z);
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
        System.out.println(Hello);

        //Лекция 1.2 мат. функции
        System.out.println(1d/2d + " =)");
        //Конкатенация
        System.out.println("Hello" + ", " + "world"+"!");
        //Интересно...
        System.out.println("2+2="+2+2);

        var configFile = new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());
    }

    private static int calculate() {
        var x = 1;
        var y = 1;
        var z = divide(x, y);
        return z;
    }

    private static int divide(int x, int y) {
        var z = x / y;
        return z;
    }
}
