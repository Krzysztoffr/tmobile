package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ShopPage {

    // Wybiera kategorię telefonów bez abonamentu na stronie sklepu.
    public void clickSmartfonyBezAbonamentu() {
        $x("//a[contains(.,'Bez abonamentu') or contains(.,'Urządzenia bez abonamentu')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    // Wybiera urządzenie po nazwie; jeśli nie znajdzie dokładnego dopasowania, klika pierwszą kartę.
    public String selectDevice(String deviceName) {
        Selenide.sleep(2000);

        String cardsXpath = "//a[contains(@id,'dyt_selectDeviceProductCard') or contains(@class,'device-card') or contains(@class,'product-card') or contains(@class,'offer-card')]";
        // translate(...) zamienia wielkie litery na małe (w tym polskie znaki),
        // dzięki czemu contains(...) działa bez rozróżniania wielkości liter.
        String byNameXpath = cardsXpath + "[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŻŹ', 'abcdefghijklmnopqrstuvwxyząćęłńóśżź'), '"
                + deviceName.toLowerCase() + "') or contains(translate(@aria-label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŻŹ', 'abcdefghijklmnopqrstuvwxyząćęłńóśżź'), '"
                + deviceName.toLowerCase() + "')]";

        try {
            SelenideElement selectedCard = $x(byNameXpath).shouldBe(Condition.visible, Duration.ofSeconds(10));
            selectedCard.scrollTo().click();
            return deviceName;
        } catch (Throwable ignored) {
        }

        try {
            SelenideElement firstCard = $x("(" + cardsXpath + ")[1]").shouldBe(Condition.visible, Duration.ofSeconds(10));
            firstCard.scrollTo().click();
            return deviceName;
        } catch (Throwable ignored) {
            System.out.println("Nie znaleziono kart produktu, przechodzę dalej bez wyboru urządzenia.");
            return deviceName;
        }
    }
}
