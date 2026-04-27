import java.io.File;

public class Hello {
    private static String Hello = "Hello, world!";

    public static void main(String[] args) {

        try {
            var x = 1;
            var y = 1;
            if(y==0){
                System.out.println("Не едели на ноль пж");
            }else {
                var z = divide(x, y);
                //System.out.println(z);
            }
        }catch (ArithmeticException e){
            System.out.println("Не едели на ноль пж");
        }
        System.out.println(Hello);

//        var configFile = new File("sandbox/build.gradle");
//        System.out.println(configFile.getAbsolutePath());
//        System.out.println(configFile.exists());
    }

    private static int divide(int x, int y) {
        var z = x / y;
        return z;
    }
}
