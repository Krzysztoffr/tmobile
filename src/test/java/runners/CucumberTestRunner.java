package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

// Uruchamia testy jako suite JUnit 5.
@Suite
// Mowi JUnit, ze ma użyć silnika Cucumber.
@IncludeEngines("cucumber")
// Wskazuje katalog z plikami .feature (classpath:features).
@SelectClasspathResource("features")
// Wskazuje pakiet z implementacją kroków Given/When/Then.
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps")
// Filtr tagów (np. pomijanie @failure-demo) jest ustawiony w pom.xml przez maven-surefire-plugin.
// Ustawia format logów Cucumber oraz generuje raporty JSON/HTML/JUnit.
@ConfigurationParameter(
	key = PLUGIN_PROPERTY_NAME,
	value = "pretty, json:target/cucumber-reports/cucumber.json, html:target/cucumber-reports/cucumber.html, junit:target/cucumber-reports/cucumber.xml"
)
// Ten plik to punkt startowy testów Cucumber uruchamianych przez JUnit 5.
public class CucumberTestRunner {
}
