package com.testing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.testing.services.ServiceTest;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ControllerTest {

    @Autowired
    ServiceTest serviceTest;

    @GetMapping(value = "/message")
    public Mono<String> getMessage(@RequestParam(name = "service") String service) {
        return serviceTest.helloService(service);
    }

    @GetMapping(value = "/exception")
    public Mono<String> getException() throws IllegalStateException {
        throw new IllegalStateException("Custom generated exception");
    }

    @GetMapping(value = "/error")
    public Mono<String> getError() {
        return serviceTest.generateError();
    }
}
