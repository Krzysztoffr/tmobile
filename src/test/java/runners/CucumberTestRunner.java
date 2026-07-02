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
// Ustawia format logów Cucumber w konsoli.
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
// Ten plik to punkt startowy testów Cucumber uruchamianych przez JUnit 5.
public class CucumberTestRunner {
}
