package com.testing.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ServiceTest.class);

    public Mono<String> helloService(String service) {
        return Mono.just("Hello, " + service + "!")
                .doOnNext(s -> logger.info("EVENT=\"" + s + "\""));
    }

    public Mono<String> generateError() {
        return Mono.just("A foo goes in a bar")
                .doOnNext(s -> {throw new IllegalStateException("Custom generated exception");})
                .onErrorReturn("Custom generated error");
    }
}
