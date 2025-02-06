package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class calendarTest extends baseTest {
    @Test
    public void setdate() {
        String date = calendarpage.selectDate(23, 4, 2000);
        Assert.assertEquals(calendarpage.getDateValue(), date);

    }

}
