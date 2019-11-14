package com.testing;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SerenityRunner.class)
public class DefaultTest {

    @Steps
    private DefaultSteps defaultSteps;

    @BeforeClass
    public static void startApp() {
        SpringApplication.run(MainApp.class);
    }

    @Test
    public void checkExceptionEndpoint() throws IOException {
        defaultSteps.theEndpoint("/exception");
        defaultSteps.theRequestIsSentToTheEndpoint();
        defaultSteps.exceptionMessageIsReceived();
    }

    @Test
    public void checkMessageEndpoint() throws IOException {
        defaultSteps.theEndpoint("/message");
        defaultSteps.hasParameter("?service=TestServiceX");
        defaultSteps.theRequestIsSentToTheEndpoint();
        defaultSteps.successfulMessageIsReceived();
    }

    @Test
    public void checkInvalidEndpoint() throws IOException {
        defaultSteps.theEndpoint("/mssage");
        defaultSteps.hasParameter("?service=TestServiceX");
        defaultSteps.theRequestIsSentToTheEndpoint();
        defaultSteps.errorCodeIsReceived(404);
    }

    @Test
    public void checkMessageEndpointInvalidParameter() throws IOException {
        defaultSteps.theEndpoint("/message");
        defaultSteps.hasParameter("?serviceX=TestServiceX");
        defaultSteps.theRequestIsSentToTheEndpoint();
        defaultSteps.errorCodeIsReceived(400);
    }

}