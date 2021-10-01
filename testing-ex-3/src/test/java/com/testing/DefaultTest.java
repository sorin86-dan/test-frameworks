package com.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.testng.Assert.*;

@SpringBootTest
public class DefaultTest {

    @BeforeClass
    public void startApp() {
        SpringApplication.run(MainApp.class);
    }

    @Test
    public void checkExceptionEndpoint() throws IOException {
        URL url = new URL("http://localhost:8081/exception");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(connection.getResponseCode(), 409);
        assertEquals(getStringFromInputStream(connection.getErrorStream()), "Custom Exception");
    }

    @Test
    public void checkMessageEndpoint() throws IOException {
        URL url = new URL("http://localhost:8081/message?service=TestServiceX");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(connection.getResponseCode(), 200);
        assertEquals(getStringFromInputStream(connection.getInputStream()), "Hello, TestServiceX!");
    }

    @Test
    public void checkInvalidEndpoint() throws IOException {
        URL url = new URL("http://localhost:8081/mssage?service=TestServiceX");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(connection.getResponseCode(), 404);
    }

    @Test
    public void checkMessageEndpointInvalidParameter() throws IOException {
        URL url = new URL("http://localhost:8081/message?serviceX=TestServiceX");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(connection.getResponseCode(), 400);
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