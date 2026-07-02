package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class HomePage {

    // Otwiera stronę główną T-Mobile i zamyka baner cookies.
    public void openHomePage() {
        Selenide.open("https://www.t-mobile.pl");
        closeCookiePopup();
    }

    // Zamyka okno cookies, jeśli się pojawi.
    public void closeCookiePopup() {
        Selenide.sleep(1500);

        String[] selectors = {
            "#didomi-notice-agree-button",
            "#didomi-notice-disagree-button",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŻŹ', 'abcdefghijklmnopqrstuvwxyząćęłńóśżź'), 'akceptuję wszystkie')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŻŹ', 'abcdefghijklmnopqrstuvwxyząćęłńóśżź'), 'akceptuję')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŻŹ', 'abcdefghijklmnopqrstuvwxyząćęłńóśżź'), 'zgadzam się')]",
                "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŻŹ', 'abcdefghijklmnopqrstuvwxyząćęłńóśżź'), 'tylko niezbędne')]",
            "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'accept all')]",
            "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'only required')]",
            "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'settings')]"
        };

        for (String selector : selectors) {
            try {
                SelenideElement element = selector.startsWith("#")
                        ? Selenide.$(selector)
                        : Selenide.$x(selector);

                if (element.exists() && element.isDisplayed()) {
                    element.shouldBe(Condition.visible).click();
                    Selenide.$("#didomi-popup").shouldBe(Condition.hidden, Duration.ofSeconds(8));
                    System.out.println("Popup cookies zamknięty.");
                    return;
                }
            } catch (Exception ignored) {
            }
        }

        System.out.println("Popup cookies nie pojawił się lub nie był dostępny.");
    }

    // Otwiera sekcję Sklep z górnej belki strony.
    public void clickShop() {
        closeCookiePopup();

        Selenide.sleep(1000);
        SelenideElement shopButton = Selenide.$x("//button[normalize-space()='Sklep' or normalize-space()='Shop']");
        shopButton.shouldBe(Condition.visible);

        try {
            shopButton.click();
        } catch (Exception e) {
            Selenide.executeJavaScript("arguments[0].scrollIntoView({block: 'center'});", shopButton);
            Selenide.sleep(500);
            Selenide.executeJavaScript("arguments[0].click();", shopButton);
        }
    }

    // Przechodzi z powrotem na stronę główną T-Mobile.
    public void goToMainPage() {
        Selenide.open("https://www.t-mobile.pl");
    }

    // Otwiera koszyk z poziomu strony.
    public void openCart() {
        Selenide.$x("//a[contains(@href,'basket')]")
                .shouldBe(Condition.visible)
                .click();
    }
}

