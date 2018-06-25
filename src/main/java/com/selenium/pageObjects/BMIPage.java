package com.selenium.pageObjects;

import com.selenium.enums.GenderEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dóra on 2018.06.25..
 */
public class BMIPage {

    @FindBy(how = How.NAME, using = "sex")
    private WebElement gender;

    @FindBy(how = How.NAME, using = "age")
    private WebElement age;

    @FindBy(how = How.NAME, using = "height")
    private WebElement height;

    @FindBy(how = How.NAME, using = "weight")
    private WebElement weight;

    @FindBy(how = How.ID, using = "bmiBtnCalculateBMI")
    private WebElement btnCalculate;

    @FindBy(how = How.ID, using = "bmiResult")
    private WebElement outResult;

    @FindBy(how = How.CSS, using = "div[class^='group1']")
    private WebElement bmiResult;

    public BMIPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setAge(double value) {
        age.sendKeys(Double.toString(value));
    }

    public void setWeight(double value) {
        weight.sendKeys(Double.toString(value));
    }

    public void setHeight(double value) {
        height.sendKeys(Double.toString(value));
    }

    public void setGender(String value) {
        this.gender.sendKeys(value);

        if (value.equals(GenderEnum.FEMALE.toString().toLowerCase())) {
            gender.findElement(By.cssSelector("option[value^='1']")).click();
        } else {
            gender.findElement(By.cssSelector("option[value^='0']")).click();
        }
    }

    public void clickCalculate() {
        btnCalculate.click();
    }

    public String bmiResultValue() {
        return bmiResult.getText();
    }
}
