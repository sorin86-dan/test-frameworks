package com.testing.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceAppBean {

    private String serviceName;

    @Autowired
    private ServiceDetailsBean serviceDetails;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceDetailsBean getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(ServiceDetailsBean serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    @Override
    public String toString() {
        return "ServiceApp{Service name: " + serviceName + ", " + serviceDetails.toString() + "}";
    }
}
