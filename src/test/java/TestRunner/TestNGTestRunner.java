package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/java/FeatureFiles",
		glue= "amazon.cart.stepDefinitions", 
		monochrome=true,
        plugin= {"html:target/cucumber.html"}
        
        )

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
