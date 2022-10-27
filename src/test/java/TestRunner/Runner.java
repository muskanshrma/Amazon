package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/FeatureFiles"},
        glue = {"StepDefinition"},
        monochrome = true,
        plugin = {"pretty", "json:Report/Cucumber.json", "junit:Report/XMLReport.xml",
                "html:Report/cucumberReport"}
)

public class Runner {

}