package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class calendarPage {
    WebDriver driver;
    By date = By.id("txtStartDate");
    By prev = By.cssSelector("div[style=''] th[class='prev']");
    By switchBtn  = By.cssSelector("div[style=''] th[class='datepicker-switch']");
    By next = By.cssSelector("div[style=''] th[class='next']");
    By yearlist = By.xpath("//span[contains(@class,'year')]");
    String yearString = "//span[contains(@class,'year') and (text()='%d')]";
    By months =By.xpath("//span[contains(@class,'month')]");
    String dayString = "//td[(@class='day') and (text()='%d')]";
          Actions action ;
          WebDriverWait wait;
    public calendarPage(WebDriver driver)
    {
        this.driver=driver;
        action= new Actions(driver);
        action.scrollByAmount(0,500).pause(300).perform();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    }

    private void selectYear(int year)
    {
        driver.findElement(date).click();
        wait.until(ExpectedConditions.elementToBeClickable(switchBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(switchBtn)).click();

        // Wait for the year list to be populated
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(yearlist));

        // Get the list of years
        List<WebElement> yearslist = driver.findElements(yearlist);
        int firstYear =Integer.parseInt(yearslist.get(0).getText());
        int lastYear =Integer.parseInt(yearslist.get(yearslist.size()-1).getText());
        int flag =1;
       while (flag==1)
       {
            if(year >= firstYear && year <= lastYear)
            {
                driver.findElement(By.xpath(String.format(yearString,year))).click();
                flag=0;
            }
            else{

                     if (year < firstYear) {

                    driver.findElement(prev).click();

                } else if (year > lastYear) {

                    driver.findElement(next).click();

                }
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(yearlist));

                // Get the list of years
                 yearslist = driver.findElements(yearlist);
                 firstYear =Integer.parseInt(yearslist.get(0).getText());
                 lastYear =Integer.parseInt(yearslist.get(yearslist.size()-1).getText());
            }


        }


    }

    private void selectMonth (int month)
    {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(months));
        List<WebElement> monthlist = driver.findElements(months);
        monthlist.get(month-1).click();
    }
    private void selectDay (int day)
    {
        driver.findElement(By.xpath(String.format(dayString,day))).click();
    }
    public String selectDate(int day , int month ,int year)
    {
        selectYear(year);
        selectMonth(month);
        selectDay(day);
        return String.format("%02d/%02d/%04d",month,day,year);
    }
    public String getDateValue()
    {
        return driver.findElement(date).getAttribute("value");
    }

}

