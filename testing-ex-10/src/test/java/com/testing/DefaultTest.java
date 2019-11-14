package com.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class DefaultTest {

    private static WebDriver webDriver;
    private static GooglePage googlePage;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        googlePage = new GooglePage(webDriver);
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }

    @Test
    public void defaultTest() {
        webDriver.get("https://www.google.com");
        googlePage.fillSearchBox("selenium webdriver");

        assertTrue(googlePage.isSearchOutputLinkPresent());
    }

}