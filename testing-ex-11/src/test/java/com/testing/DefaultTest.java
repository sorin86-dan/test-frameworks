package com.testing;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class DefaultTest {

    @Test
    public void defaultTest() {
        Configuration.browser = "chrome";

        open("https://t3ch5tuff5.wordpress.com");

        $$(By.xpath("//ul[@id='menu-primary-1']/li")).get(1).click();
        $(By.className("entry-title")).shouldBe(Condition.visible).shouldHave(Condition.text("Blog"));

        $$(By.xpath("//ul[@id='menu-primary-1']/li")).get(2).click();
        $(By.className("entry-title")).shouldBe(Condition.visible).shouldHave(Condition.text("About"));

        $$(By.xpath("//ul[@id='menu-primary-1']/li")).get(3).click();
        $(By.className("entry-title")).shouldBe(Condition.visible).shouldHave(Condition.text("Contact"));
    }

}