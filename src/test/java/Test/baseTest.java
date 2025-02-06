package Test;

import Pages.calendarPage;
import Test.calendarTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class baseTest {
    WebDriver driver;
    calendarPage calendarpage ;
    @BeforeMethod
    public void setup()
    {
        driver=new ChromeDriver();
        driver.get("https://www.bestcase.com/date-calculator/");
        driver.manage().window().maximize();
        calendarpage = new calendarPage(driver);
    }
    @AfterMethod
    public void threadDown()
    {
        // driver.quit();
    }
}
