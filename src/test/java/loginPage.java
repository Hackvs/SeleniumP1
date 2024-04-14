import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;




public class loginPage {
    public static WebDriver driver = new ChromeDriver();

    @BeforeTest
    void setUp(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        String url = "https://katalon-demo-cura.herokuapp.com/";
        driver.manage().window().maximize();
        driver.get(url);

    }
    @Test
    void login() throws InterruptedException {
        driver.findElement(By.id("btn-make-appointment")).click();
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
        sleep(200);
        System.out.println("Login successful");
    }

    @Test
    void makingAnAppointment() throws InterruptedException {
        WebElement dropDownFacility =  driver.findElement(By.xpath("/html/body/section/div/div/form/div[1]/div/select"));
        if (dropDownFacility.getTagName().equalsIgnoreCase("select")) {
            Select facility = new Select(dropDownFacility);
            facility.selectByIndex(1);
        }
        driver.findElement(By.xpath("/html/body/section/div/div/form/div[2]/div/label/input")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/form/div[3]/div/label[2]/input")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/form/div[4]/div/div/input"));

    }

    @AfterTest
    void tearDown(){
        driver.close();
    }
}
