package com.testing;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;

public class DefaultTest {

    @Test
    public void defaultTest() {
        open("https://www.google.com");
        $(By.xpath("//input[@name='q']")).setValue("selenium webdriver");
        $(By.xpath("//input[@name='q']")).submit();
        $(By.xpath("//h3[@class='LC20lb']")).shouldBe(Condition.visible);
    }

}