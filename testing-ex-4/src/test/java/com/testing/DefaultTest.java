package com.testing;

import com.testing.controllers.ControllerTest;
import com.testing.services.ServiceTest;
import com.testing.utils.ErrorHandler;
import com.testing.utils.GlobalErrorAttributes;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = ControllerTest.class)
@Import({ServiceTest.class, ErrorHandler.class, GlobalErrorAttributes.class})
public class DefaultTest {

    @Autowired
    private WebTestClient webTestClient;

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void checkMessageEndpoint() {
        webTestClient.get()
                .uri("/message?service=TestServiceX")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .value(equalTo("Hello, TestServiceX!"));

        outputCapture.expect(containsString("EVENT=\"Hello, TestServiceX!\""));
    }

    @Test
    public void checkInvalidEndpoint() throws IOException {
        webTestClient.get()
                .uri("/mssage?service=TestServiceX")
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.path").isEqualTo("/mssage")
                .jsonPath("$.status").isEqualTo("BAD_REQUEST")
                .jsonPath("$.error").isEqualTo("Not Found")
                .jsonPath("$.message").isEqualTo("Custom Exception");
    }

    @Test
    public void checkMessageEndpointInvalidParameter() throws IOException {
        webTestClient.get()
                .uri("/message?serviceX=TestServiceX")
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.path").isEqualTo("/message")
                .jsonPath("$.status").isEqualTo("BAD_REQUEST")
                .jsonPath("$.error").isEqualTo("Bad Request")
                .jsonPath("$.message").isEqualTo("Custom Exception");
    }

    @Test
    public void checkExceptionEndpoint() {
        webTestClient.get()
                .uri("/exception")
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody()
                .jsonPath("$.path").isEqualTo("/exception")
                .jsonPath("$.status").isEqualTo("BAD_REQUEST")
                .jsonPath("$.error").isEqualTo("Internal Server Error")
                .jsonPath("$.message").isEqualTo("Custom Exception");
    }

    @Test
    public void checkErrorEndpoint() {
        webTestClient.get()
                .uri("/error")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .value(equalTo("Custom generated error"));
    }

}