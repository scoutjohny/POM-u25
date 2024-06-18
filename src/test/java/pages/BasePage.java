package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BasePage {

    WebDriver driver;
    final int maxRetries = 3;

    public BasePage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[data-label='Naslovna']")
    WebElement homeButton;

    public void goToHomePage(){
        homeButton.click();
    }

    WebDriverWait webDriverWait;
    public void click(WebElement element){
           webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
           webDriverWait.until(ExpectedConditions.visibilityOf(element));
           webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

           Actions actions = new Actions(driver);
           actions.moveToElement(element)
                  .build()
                  .perform();

           element.click();
    }

    BaseTest baseTest = new BaseTest();
    public void click(WebElement element, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        int retryCount=0;
        while (retryCount<maxRetries){
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element)
                        .build()
                        .perform();

                element.click();
                System.out.println("Clicked: "+log);
                break;
            }catch (Exception e){
                retryCount++;
                System.out.println("Retry: "+retryCount+" to click on "+log);
                if(retryCount==maxRetries){
                    baseTest.reportScreenshot(getCurrentDateTime()+"failedClick", "Failed to click");
                    throw new Exception(getCurrentDateTime()+"Failed to click element: "+log);
                }
            }
        }
    }

    public String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
