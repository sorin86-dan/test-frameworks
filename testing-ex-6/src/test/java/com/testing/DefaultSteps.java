package com.testing;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class DefaultSteps {

    private String endpoint;
    private URL url;
    private HttpURLConnection connection;

    @Given("the {string} endpoint")
    public void theEndpoint(String message) {
        endpoint = "/" + message;
    }

    @And("has {string}")
    public void hasParameter(String parameter) {
        endpoint += parameter;
    }

    @When("the request is sent to the endpoint")
    public void theRequestIsSentToTheEndpoint() throws IOException {
        url = new URL("http://localhost:8080" + endpoint);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
    }

    @Then("successful message is received")
    public void successfulMessageIsReceived() throws IOException {
        assertEquals(200, connection.getResponseCode());
        assertEquals("Hello, TestServiceX!", getStringFromInputStream(connection.getInputStream()));
    }

    @Then("error code {int} is received")
    public void errorCodeIsReceived(int errorcode) throws IOException {
        assertEquals(errorcode, connection.getResponseCode());
    }

    @Then("exception message is received")
    public void exceptionMessageIsReceived() throws IOException {
        assertEquals(409, connection.getResponseCode());
        assertEquals("Custom Exception", getStringFromInputStream(connection.getErrorStream()));
    }


    private String getStringFromInputStream(InputStream stream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();

        try (Reader reader = new BufferedReader(new InputStreamReader
                (stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }

}
