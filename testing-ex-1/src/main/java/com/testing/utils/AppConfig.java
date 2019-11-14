package com.testing.utils;

import com.testing.beans.ServiceAppBean;
import com.testing.beans.ServiceDetailsBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.testing.services", "com.testing.beans"})
public class AppConfig {

    @Bean
    public ServiceDetailsBean serviceDetails() {
        ServiceDetailsBean serviceDetails = new ServiceDetailsBean();

        serviceDetails.setEndpoint("message");
        serviceDetails.setPort(8080);
        serviceDetails.setProtocol("http");

        return serviceDetails;
    }

    @Bean
    public ServiceAppBean serviceApp() {
        ServiceAppBean serviceApp = new ServiceAppBean();

        serviceApp.setServiceDetails(serviceDetails());
        serviceApp.setServiceName("TestService");

        return serviceApp;
    }

}
