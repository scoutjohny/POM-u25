package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePagePF;

public class polovniAutomobili extends BaseTest{

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) throws Exception {
        init(browser);
    }

    @AfterMethod
    @Parameters({"quit"})
    public void tearDown(String quit){
        if(quit.equalsIgnoreCase("Yes")){
            quit();
        }
    }

    @Test
    @Parameters({"env"})
    public void searchCar(String env) throws Exception {
        openApp(env);
        HomePagePF homePagePF = new HomePagePF(driver);
        homePagePF.searchVehicles("Alfa Romeo", "147", "5000");
        Assert.assertEquals(driver.findElement(By.cssSelector(".searchTitle")).getText(),"Alfa Romeo 145 od 2022 - Cena do 5000€");
    }
}
