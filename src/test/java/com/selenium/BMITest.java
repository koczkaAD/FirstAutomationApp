package com.selenium;

import com.selenium.enums.WebDriverEnum;
import com.selenium.pageObjects.BMIPage;
import com.selenium.pageObjects.GoogleSearchPage;
import com.selenium.utility.WebDriverFactory;
import com.selenium.utility.reader.ExcelReader;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dóra on 2018.06.25..
 */
public class BMITest {

    private WebDriver driver;
    private WebDriverEnum webDriverEnum = WebDriverEnum.CHROME;

    @BeforeTest
    public void beforeTest(){
        driver = WebDriverFactory.createWebdriver(webDriverEnum);
    }

    @DataProvider(name = "bmiTestDatas")
    public Object[][] createDatas() {
        ExcelReader excelReader = new ExcelReader("src/Main/resources/testPlans/testDatas.xlsx");
        return excelReader.passData("BMI_DATA");
    }

    @Test(dataProvider = "bmiTestDatas")
    public void bmiTests(String gender, double age, double height, double weight){
        Logger log = Logger.getLogger(BMITest.class.getName());
        log.setLevel(Level.INFO);
        log.info("BMI test run...");
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
        BMIPage bmiPage = new BMIPage(driver);

        driver.get("https://www.google.hu");

        googleSearchPage.getTxtSearch().sendKeys("BMI kalkulátor");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        googleSearchPage.getBtnSearch().sendKeys(Keys.ENTER);

        //BMI kalkulátor linkre kattintás
        WebElement bmiLink = driver.findElement(By.cssSelector("a[href='http://www.xn--kalriaguru-ibb.hu/kalkulator/bmi-kalkulator/bmi-kalkulator.php']"));
        bmiLink.click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        //Az oldalon scroll lefelé
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        //BMI kalkulátor oldal adatokkal feltöltése
        bmiPage.setGender(gender);
        bmiPage.setAge(age);
        bmiPage.setHeight(height);
        bmiPage.setWeight(weight);

        bmiPage.clickCalculate();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        log.info("BMI value: " + bmiPage.bmiResultValue());

        log.info("BMI test final...");
    }

    @AfterTest
    public void afterTest(){
        driver.close();
    }
}
