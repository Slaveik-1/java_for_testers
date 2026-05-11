package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    protected static ApplicationManager app;


    //Инициализация
    @BeforeEach
    public void setUp() {
        if(app == null){
            app = new ApplicationManager();
        }
        //Заве
        app.init(System.getProperty("browser","firefox"));
    }

}
