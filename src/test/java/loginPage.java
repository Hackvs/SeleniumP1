import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Test (priority = 0)
    void login() throws InterruptedException {
        driver.findElement(By.id("btn-make-appointment")).click();
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
        sleep(200);
        System.out.println("Login successful");
    }

    @Test (priority = 1)
    void makingAnAppointment() throws InterruptedException {
        WebElement dropDownFacility =  By.xpath("/html/body/section/div/div/form/div[1]/div/select").findElement(driver);
        if (dropDownFacility.getTagName().equalsIgnoreCase("select")) {
            Select facility = new Select(dropDownFacility);
            facility.selectByIndex(1);
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        driver.findElement(By.xpath("/html/body/section/div/div/form/div[2]/div/label/input")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/form/div[3]/div/label[2]/input")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/form/div[4]/div/div/input")).sendKeys(date1);
        Thread.sleep(50);
        driver.findElement(By.xpath("//textarea[@id='txt_comment']")).sendKeys("Book an appointment for my uncle. " +
                "Thanks!!");
        driver.findElement(By.xpath("//button[@id='btn-book-appointment']")).click();
        Thread.sleep(50);

        String facility = driver.findElement(By.xpath("//p[@id='facility']")).getText();
        String hospitalReadmission  = driver.findElement(By.xpath("//p[@id='hospital_readmission']")).getText();
        String healthcareProgram = driver.findElement(By.xpath("//p[@id='program']")).getText();
        String visitDate = driver.findElement(By.xpath("//p[@id='visit_date']")).getText();
        String comment = driver.findElement(By.xpath("//p[@id='comment']")).getText();
        System.out.println("------------Appointment Information-------------");
        System.out.println("Facility : "+facility);
        System.out.println("Apply for hospital readmission : "+hospitalReadmission);
        System.out.println("Healthcare Program : "+healthcareProgram);
        System.out.println("Visit Date : "+visitDate);
        System.out.println("Comment : "+comment);
        System.out.println("------------------------------------------------");
        Thread.sleep(100);
        driver.findElement(By.cssSelector(".btn.btn-default")).click();
        Thread.sleep(100);
    }

    @Test (priority = 2)
    void logOut() throws InterruptedException {
        driver.findElement(By.xpath("//a[@id='menu-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='authenticate.php?logout']")).click();
        Thread.sleep(50);
        System.out.println("Logout Successful!!!");
    }
    @AfterTest
    void tearDown(){
        driver.close();
    }
}
