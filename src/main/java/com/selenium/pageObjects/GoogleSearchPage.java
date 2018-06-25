package com.selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dóra on 2018.06.25..
 */
public class GoogleSearchPage {

    @FindBy(how = How.ID, using = "Email")
    private WebElement txtEmail;

    @FindBy(how = How.ID, using = "next")
    private WebElement buttonNext;

    @FindBy(how = How.ID, using = "lst-ib")
    private WebElement txtSearch;

    @FindBy(how = How.NAME, name = "btnK")
    private WebElement btnSearch;

    public GoogleSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public WebElement getTxtEmail() {
        return txtEmail;
    }

    public WebElement getButtonNext() {
        return buttonNext;
    }

    public WebElement getTxtSearch() {
        return txtSearch;
    }

    public WebElement getBtnSearch() {
        return btnSearch;
    }

}
