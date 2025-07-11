package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import java.util.logging.LogManager; // Used to control default Java logging
import org.slf4j.bridge.SLF4JBridgeHandler; // Bridges java.util.logging to SLF4J (for Log4j2, etc.)

// Define Cucumber options
@CucumberOptions(
		features = "src/test/java/features", 
		glue = { "stepdefinitions", "hooks" }, 
		tags = "@HealthInsurance", 
		plugin = { "pretty", "html:target/cucumber-reports.html"}, 
		monochrome = true)
// Main runner class for executing Cucumber scenarios using TestNG
public class TestRunner extends AbstractTestNGCucumberTests {

	// Static block runs when the class is loaded (before any method)
	static {
		// Reset the default Java Util Logging (JUL) manager
		LogManager.getLogManager().reset(); // Ensures no default console logs interfere

		// Remove any existing handlers attached to the root logger
		SLF4JBridgeHandler.removeHandlersForRootLogger(); // Prevents duplicate logs

		// Bridge JUL (java.util.logging) to SLF4J (which then goes to Log4j2 or another
		// backend)
		SLF4JBridgeHandler.install(); // Routes JUL logs through your preferred logging framework
	}
}