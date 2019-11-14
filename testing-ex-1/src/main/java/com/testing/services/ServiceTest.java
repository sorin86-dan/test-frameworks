package com.testing.services;

import com.testing.beans.ServiceAppBean;
import org.springframework.stereotype.Service;

@Service
public class ServiceTest {

    public ServiceTest() {}

    public void helloService(ServiceAppBean serviceApp) {
        System.out.println("Hello, " + serviceApp.getServiceName() + "!");
        System.out.println(serviceApp);
    }
}
