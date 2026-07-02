Feature: Wybranie telefonu z listy ofert

  Scenario: Wybranie telefonu z listy ofert
    Given otwieram przeglądarkę
    And przechodzę na stronę T-Mobile
    When wybieram sklep z górnej belki
    And klikam Bez abonamentu w sekcji Smartfony
    And wybieram urządzenie 'Samsung Galaxy S26'
    And dodaję produkt do koszyka
    Then cena w koszyku zgadza się z ceną produktu
    And przechodzę na stronę główną
    And otwieram koszyk
    Then koszyk zawiera wybrane urządzenie
