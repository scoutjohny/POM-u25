package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePagePF extends BasePage{

    public HomePagePF(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".paBlueButtonPrimary")
    WebElement acceptCookies;

    @FindBy(css = "[title=' Sve marke']")
    WebElement brands;

    @FindBy(css = "[title=' Svi modeli']")
    WebElement models;

    @FindBy(css = "#price_to")
    WebElement priceTo;

    @FindBy(css = "[name='submit_1']")
    WebElement searchButton;

    public void acceptCookies(){
        acceptCookies.click();
    }
    public void selectBrand (String brand) throws Exception {
        click(brands);
        click(driver.findElement(By.xpath("//label[text()='"+brand+"']")),"Car brand clicked: "+brand);
    }

    public void selectModel(String model) throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(models));
        click(models);
        click(driver.findElement(By.xpath("//label[contains(text(),'"+model+"')]")),"Car model clicked: "+model);
    }

    public void endPriceSelect(String price){
        click(priceTo);
        driver.findElement(By.cssSelector("#price_to")).sendKeys(price);
    }

    public void clickOnSearchButton(){
        searchButton.click();
    }

    public void searchVehicles(String brand, String model, String price) throws Exception {
        acceptCookies();
        selectBrand(brand);
        selectModel(model);
        endPriceSelect(price);
        clickOnSearchButton();
    }
}
