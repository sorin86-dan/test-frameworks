package com.testing;

import com.testing.beans.ServiceAppBean;
import com.testing.beans.ServiceDetailsBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.testing.utils.AppConfig;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class DefaultTest {

    @Test
    public void checkTestServiceWithContextDefaultValues() {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ServiceAppBean serviceAppBean = context.getBean("serviceApp", ServiceAppBean.class);
            serviceAppBean.setServiceName("DefaultService01");

            assertEquals("Service name is correct", "DefaultService01", serviceAppBean.getServiceName());
            assertEquals("Endpoint is correct", "message", serviceAppBean.getServiceDetails().getEndpoint());
            assertEquals("Port is correct", 8080, serviceAppBean.getServiceDetails().getPort());
            assertEquals("Protocol is correct", "http", serviceAppBean.getServiceDetails().getProtocol());
        }

    }

    @Test
    public void checkTestServiceWithContext() {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ServiceAppBean serviceAppBean = context.getBean("serviceApp", ServiceAppBean.class);
            ServiceDetailsBean serviceDetailsBean = context.getBean("serviceDetails", ServiceDetailsBean.class);
            serviceAppBean.setServiceName("DefaultService01");
            serviceAppBean.setServiceDetails(serviceDetailsBean);
            serviceDetailsBean.setPort(8181);

            assertEquals("Service name is incorrect", "DefaultService01", serviceAppBean.getServiceName());
            assertEquals("Endpoint is incorrect", "message", serviceAppBean.getServiceDetails().getEndpoint());
            assertEquals("Port is incorrect", 8181, serviceAppBean.getServiceDetails().getPort());
            assertEquals("Protocol is incorrect", "http", serviceAppBean.getServiceDetails().getProtocol());
        }

    }

    @Test
    public void checkTestServiceWithoutContext() {
            ServiceAppBean serviceApp = new ServiceAppBean();
            ServiceDetailsBean serviceDetails = new ServiceDetailsBean();
            serviceApp.setServiceName("DefaultService01");
            serviceApp.setServiceDetails(serviceDetails);
            serviceDetails.setPort(8585);

            assertEquals("Service name is incorrect", "DefaultService01", serviceApp.getServiceName());
            assertEquals("Endpoint is incorrect", null, serviceApp.getServiceDetails().getEndpoint());
            assertEquals("Port is incorrect", 8585, serviceApp.getServiceDetails().getPort());
            assertEquals("Protocol is incorrect", null, serviceApp.getServiceDetails().getProtocol());
    }
}
