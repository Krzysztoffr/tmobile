package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {

    public String getProductPrice() {
        Selenide.sleep(2000);

        String[] selectors = {
                "//span[contains(@class,'actualText') or contains(@class,'actualPrice') or contains(@class,'price')][contains(.,'zł')]",
                "//div[contains(text(),'Pełna kwota za urządzenie')]/following::span[contains(.,'zł')][1]",
                "//span[contains(.,'zł')][not(ancestor::div[contains(@class,'payment')])][1]"
        };

        for (String selector : selectors) {
            try {
                SelenideElement priceElement = $x(selector);
                priceElement.shouldBe(Condition.visible, Duration.ofSeconds(8));
                return priceElement.getText().trim();
            } catch (Exception ignored) {
            }
        }

        throw new AssertionError("Nie znaleziono ceny produktu.");
    }

    public void clickAddToCart() {
        Selenide.sleep(1000);

        String[] visibleSelectors = {
                "//button[@id='dyt_addDeviceToCart']",
                "//button[contains(.,'Dodaj do koszyka')]",
                "//a[contains(.,'Dodaj do koszyka')]"
        };

        for (String selector : visibleSelectors) {
            try {
                SelenideElement addButton = $x(selector).shouldBe(Condition.visible, Duration.ofSeconds(5));
                addButton.scrollTo();
                addButton.click();
                return;
            } catch (Throwable ignored) {
            }
        }

        String[] existingSelectors = {
                "//button[@id='dyt_addDeviceToCart']",
                "//*[@data-qa='PRD_AddToBasket']"
        };

        for (String selector : existingSelectors) {
            try {
                SelenideElement addButton = $x(selector);
                if (addButton.exists()) {
                    Selenide.executeJavaScript("arguments[0].scrollIntoView({block:'center'});", addButton);
                    Selenide.executeJavaScript("arguments[0].click();", addButton);
                    return;
                }
            } catch (Throwable ignored) {
            }
        }

        throw new AssertionError("Nie znaleziono przycisku Dodaj do koszyka.");
    }
}
