package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private String browser;
    private Properties properties;
    private SessionHelper session;
    private HttpSessionHelper httpSession;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mail;
    private UiHellper uiHellper;
    private JamesApiHelper jamesApiHelper;


    //init...(выбор браузера/остановка сессии/стартовая страница)
    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;

        }


    public WebDriver driver(){
        if (driver==null){
            if ("firefox".equals(browser)){
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            }
            else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseURL"));
            driver.manage().window().setSize(new Dimension(1936, 1048));
            //  session().login(properties.getProperty("web.userName"), properties.getProperty("web.password"));
        }
        return driver;
    }

    public SessionHelper session(){
        if (session==null){
            session=new SessionHelper(this);
        }
        return session;
    }

    public HttpSessionHelper http() {
        if (httpSession==null){
            httpSession=new HttpSessionHelper(this);
        }
        return httpSession;
    }

    public JamesCliHelper jamesCli() {
        if (jamesCliHelper==null){
            jamesCliHelper=new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mail() {
        if (mail==null){
            mail=new MailHelper(this);
        }
        return mail;
    }

    public UiHellper uiHellper() {
        if (uiHellper==null){
            uiHellper=new UiHellper(this);
        }
        return uiHellper;
    }

    public JamesApiHelper jamesApiHelper() {
        if (jamesApiHelper==null){
            jamesApiHelper=new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }

    public String getProperties(String name) {
        return properties.getProperty(name);
    }
}
