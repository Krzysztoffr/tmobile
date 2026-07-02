package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {

    public String getCartPrice() {
        Selenide.sleep(2000);

        String[] selectors = {
                "//span[contains(@class, 'price') and contains(.,'zł')]",
                "//div[contains(@class, 'cart')]//span[contains(., 'zł')]",
                "//div[contains(@class, 'item')]//span[contains(., 'zł')]",
                "(//span[contains(., 'zł')])[1]"
        };

        for (String selector : selectors) {
            try {
                SelenideElement priceElement = $x(selector).shouldBe(Condition.visible, Duration.ofSeconds(8));
                return priceElement.getText().trim();
            } catch (Throwable ignored) {
            }
        }

        throw new AssertionError("Nie znaleziono ceny w koszyku.");
    }

    public String getProductName() {
        Selenide.sleep(2000);

        String[] selectors = {
            "//h3[contains(@class,'dt_title')]",
            "//*[@data-qa='BASKET_ProductName']",
                "//div[contains(@class, 'product-name') or contains(@class, 'item-name')]",
                "//div[contains(@class, 'cart')]//a[contains(@href, 'product')]",
                "(//div[contains(@class,'cart')]//*[self::a or self::span][string-length(normalize-space()) > 5])[1]"
        };

        for (String selector : selectors) {
            try {
                SelenideElement nameElement = $x(selector).shouldBe(visible, Duration.ofSeconds(8));
                return nameElement.getText().trim();
            } catch (Throwable ignored) {
            }
        }

        throw new AssertionError("Nie znaleziono nazwy produktu w koszyku.");
    }
}

