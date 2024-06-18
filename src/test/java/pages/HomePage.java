package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;

    By brands = By.cssSelector("[title=' Sve marke']");

    public HomePage (WebDriver driver){
        this.driver = driver;
    }

    //WebElement brands = driver.findElement(By.cssSelector("[title=' Sve marke']"));
    public void selectBrands (){
        driver.findElement(this.brands).click();
    }
}
