package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class TestHooks {

    // Wybiera przeglądarkę na czas uruchomienia: domyślnie Chrome, a -Dbrowser=firefox przełącza na Firefox.
    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        String browser = System.getProperty("browser", "chrome");
        if ("firefox".equalsIgnoreCase(browser)) {
            Configuration.browser = "firefox";
        } else {
            Configuration.browser = "chrome";
        }
        scenario.log("Browser selected: " + Configuration.browser);
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                String currentUrl = "unknown";
                if (WebDriverRunner.hasWebDriverStarted()) {
                    currentUrl = WebDriverRunner.url();
                }
                scenario.log("Scenario failed: " + scenario.getName());
                scenario.log("Current URL at failure: " + currentUrl);
            }
        } catch (Exception e) {
            scenario.log("Failed to prepare failure log: " + e.getMessage());
        } finally {
            // Always close browser to satisfy cleanup requirement after execution.
            Selenide.closeWebDriver();
        }
    }
}