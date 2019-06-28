import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WirtualnaTest {
    private WebDriver webDriver;
    private String login;
    private String passwd;
    private String urlGet;

    @Before
    public void setUp(){
        urlGet="https://profil.wp.pl/login.html?zaloguj=poczta";
        login="tomek";
        passwd="Qwerty";

    }

    @Test
    public void is_Login_Fail(){
        DesiredCapabilities desiredCapabilities=DesiredCapabilities.firefox();
        URL url=null;
        try {
            url=new URL("http://localhost/wd/hub"); //docker grid
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (webDriver==null){
            webDriver=new RemoteWebDriver(url,desiredCapabilities);
            webDriver.get(urlGet);
            webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            WebElement loginField=webDriver.findElement(By.name("login_username"));
            loginField.clear();
            loginField.sendKeys(login);
            WebElement passwordField=webDriver.findElement(By.id("password"));
            passwordField.clear();
            passwordField.sendKeys(passwd);
            webDriver.findElement(By.id("btnSubmit")).click();
            Assert.assertTrue(webDriver.getPageSource().contains("Niestety podany login lub has"));
        }
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }

}
