package com.testing;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnitReportingRunner.class)
public class DefaultStory extends JUnitStories {

    public DefaultStory() {
        super();
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration();
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new DefaultSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("stories/DefaultStory.story");
    }

    @BeforeClass
    public static void startApp() {
        SpringApplication.run(MainApp.class);
    }

}