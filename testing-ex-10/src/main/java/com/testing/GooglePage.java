package com.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GooglePage extends PageFactory {

    private WebDriver webDriver;

    public final static String SEARCH_BOX = "//input[@name='q']";
    public final static String SEARCH_OUTPUT_LINK = "//h3[@class='LC20lb']";

    @FindBy(how = How.XPATH, using = SEARCH_BOX)
    private WebElement searchBox;

    @FindBy(how = How.XPATH, using = SEARCH_OUTPUT_LINK)
    private WebElement searchOutputLink;

    public GooglePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void fillSearchBox(String text) {
        searchBox.clear();
        searchBox.sendKeys(text);
        searchBox.submit();
    }

    public boolean isSearchOutputLinkPresent() {
        return searchOutputLink.isDisplayed();
    }
}
