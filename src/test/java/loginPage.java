import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.List;

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
        WebElement dropDownFacility =  By.xpath("/html/body/section/div/div/form/div[1]/div/select").findElement(driver);
        if (dropDownFacility.getTagName().equalsIgnoreCase("select")) {
            Select facility = new Select(dropDownFacility);
            facility.selectByIndex(1);
        }
        driver.findElement(By.xpath("/html/body/section/div/div/form/div[2]/div/label/input")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/form/div[3]/div/label[2]/input")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/form/div[4]/div/div/input"));

        String date = "23/06/2025";

        String[] date_dd_MM_yyyy = (date.split(" ")[0]).split("/");

        int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);
        //For selecting date
        WebElement date_input = driver.findElement(By.xpath("/html/body/section/div/div/form/div[4]/div/div/input"));
        date_input.click();
        //button for mid link
        WebElement midLink = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[2]/th[2]"));

        //button for next year
        WebElement nextYear = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[2]/th[3]"));

        //button for next year
        WebElement prevYear = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[2]/th[1]"));

        midLink.click();
        if(yearDiff!=0){

            //if you have to move next year

            if(yearDiff>0){

                for(int i=0;i< yearDiff;i++){

                    System.out.println("Year Diff->"+i);

                    nextYear.click();

                }

            }

            //if you have to move previous year

            else {

                for(int i=0;i< (yearDiff*(-1));i++){

                    System.out.println("Year Diff->"+i);

                    prevYear.click();

                }

            }

        }

        Thread.sleep(1000);

        //Get all months from calendar to select correct one
        List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath("/html/body/div[2]/div[2]/table/thead/tr[2]/th[2]"));

        list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();

        Thread.sleep(1000);

        //get all dates from calendar to select correct one

        List<WebElement> list_AllDateToBook = driver.findElements(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr/td/span[8]"));

        list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();


    }

    @AfterTest
    void tearDown(){
        driver.close();
    }
}
