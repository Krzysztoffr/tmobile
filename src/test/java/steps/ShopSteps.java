package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.*;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShopSteps {

    HomePage home = new HomePage();
    ShopPage shop = new ShopPage();
    ProductPage product = new ProductPage();
    CartPage cart = new CartPage();

    String selectedProductName;
    String productPrice;

    @Given("otwieram przeglądarkę")
    public void openBrowser() {
        Selenide.open();
    }

    @Given("przechodzę na stronę T-Mobile")
    public void goToTMobile() {
        home.openHomePage();
    }

    @When("wybieram sklep z górnej belki")
    public void chooseShop() {
        home.clickShop();
    }

    @When("klikam Bez abonamentu w sekcji Smartfony")
    public void clickBezAbonamentu() {
        shop.clickSmartfonyBezAbonamentu();
    }

    @When("wybieram urządzenie {string}")
    public void chooseDevice(String deviceName) {
        // deviceName pochodzi z kroku w .feature: "wybieram urządzenie {string}".
        selectedProductName = shop.selectDevice(deviceName);
        productPrice = product.getProductPrice();
    }

    @When("dodaję produkt do koszyka")
    public void addToCart() {
        product.clickAddToCart();
    }

    @Then("cena w koszyku zgadza się z ceną produktu")
    public void verifyPrice() {
        assertEquals(productPrice, cart.getCartPrice());
    }

    @Then("przechodzę na stronę główną")
    public void goToMainPage() {
        home.goToMainPage();
    }

    @Then("otwieram koszyk")
    public void openCart() {
        home.openCart();
    }

    @Then("koszyk zawiera wybrane urządzenie")
    public void verifyProductInCart() {
        String cartProductName = cart.getProductName();
        assertTrue(cartProductName != null && !cartProductName.isBlank(),
                "Nie udało się odczytać nazwy produktu z koszyka.");

        String expected = normalizeText(selectedProductName);
        String actual = normalizeText(cartProductName);

        assertTrue(actual.contains(expected) || expected.contains(actual),
                "Nazwa produktu w koszyku nie zgadza się. Oczekiwano: " + selectedProductName + ", Odczytano: " + cartProductName);
    }

    private String normalizeText(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace('\u00A0', ' ')
                .replaceAll("\\s+", " ")
                .trim()
                .toLowerCase();
    }
}

