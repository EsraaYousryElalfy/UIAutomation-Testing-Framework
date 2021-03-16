package cucumber.runner;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features",
        glue = {
                "src/test/java/cucumber/runner"
                , "src/test/java/cucumber/stepDef"
        },
        plugin = {"pretty", "html:target/cucumber-html-report"})

public class TestRunner {
}

