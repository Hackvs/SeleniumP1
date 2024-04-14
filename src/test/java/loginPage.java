import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class loginPage {
    @Test
    void setUp(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://katalon-demo-cura.herokuapp.com/";
        driver.manage().window().maximize();
        driver.get(url);
    }

}
