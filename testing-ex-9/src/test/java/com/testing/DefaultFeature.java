package com.testing;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;

@RunWith(Karate.class)
@KarateOptions(features = "src/test/resources/features")
public class DefaultFeature {

//    @BeforeClass
//    public static void startApp() {
//        SpringApplication.run(MainApp.class);
//    }

}