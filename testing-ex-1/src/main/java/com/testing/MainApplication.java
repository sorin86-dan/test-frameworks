package com.testing;

import com.testing.beans.ServiceAppBean;
import com.testing.beans.ServiceDetailsBean;
import com.testing.services.ServiceTest;
import com.testing.utils.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ServiceTest serviceTest = new ServiceTest();
            ServiceAppBean serviceAppBean1 = context.getBean("serviceApp", ServiceAppBean.class);
            ServiceDetailsBean serviceDetailsBean = context.getBean("serviceDetails", ServiceDetailsBean.class);
            ServiceAppBean serviceAppBean2 = context.getBean("serviceApp", ServiceAppBean.class);
            ServiceDetailsBean serviceDetails = new ServiceDetailsBean();
            ServiceAppBean serviceApp = new ServiceAppBean();

            serviceAppBean1.setServiceName("DefaultService01");
            serviceTest.helloService(serviceAppBean1);

            System.out.println("-----------------------------------------------------------------------");

            serviceDetailsBean.setPort(8081);
            serviceAppBean2.setServiceName("DefaultService02");
            serviceAppBean2.setServiceDetails(serviceDetailsBean);
            serviceTest.helloService(serviceAppBean1);
            serviceTest.helloService(serviceAppBean2);

            System.out.println("-----------------------------------------------------------------------");

            serviceDetails.setPort(8888);
            serviceApp.setServiceDetails(serviceDetails);
            serviceTest.helloService(serviceAppBean1);
            serviceTest.helloService(serviceAppBean2);
            serviceTest.helloService(serviceApp);
        }
    }
}
