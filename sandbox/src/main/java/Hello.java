import java.io.File;

public class Hello {
    private static String Hello = "Hello, world!";

    public static void main(String[] args) {
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
}
