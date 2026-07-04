# TMobile - Testy automatyczne BDD (Selenide + Cucumber)

Projekt realizuje testy UI w podejsciu BDD z wykorzystaniem Cucumber, Selenide i JUnit 5.

## Wymagania

- Java 21+
- Chrome lub Firefox

Maven nie musi byc dodany do `PATH` (projekt zawiera Maven Wrapper).
Na Windows zalecane jest uruchamianie przez `mvnw.cmd`.
Jesli pracujesz na Windows, uzywaj tylko komend z `mvnw.cmd`.

## Uruchamianie testow

Uruchomienie domyslne (bez scenariusza technicznego `@failure-demo`):

- Windows PowerShell (zalecane):

```powershell
.\mvnw.cmd test
```

- Windows Git Bash:

```bash
./mvnw.cmd test
```

- Linux / macOS (bash/zsh, tylko te systemy):

```bash
./mvnw test
```

Uruchomienie na Firefox:

- Windows PowerShell (zalecane):

```powershell
.\mvnw.cmd test "-Dbrowser=firefox"
```

- Windows Git Bash:

```bash
./mvnw.cmd test -Dbrowser=firefox
```

- Linux / macOS (bash/zsh, tylko te systemy):

```bash
./mvnw test -Dbrowser=firefox
```

Uruchomienie scenariusza kontrolowanego bledu (do walidacji hooka `@After`):

- Windows PowerShell (zalecane):

```powershell
.\mvnw.cmd test "-Dcucumber.filter.tags=@failure-demo"
```

- Windows Git Bash:

```bash
./mvnw.cmd test -Dcucumber.filter.tags=@failure-demo
```

- Linux / macOS (bash/zsh, tylko te systemy):

```bash
./mvnw test -Dcucumber.filter.tags=@failure-demo
```

## Raporty

Po uruchomieniu testow raporty znajduja sie w:

- `target/surefire-reports/runners.CucumberTestRunner.txt`
- `target/surefire-reports/TEST-runners.CucumberTestRunner.xml`
- `target/cucumber-reports/cucumber.json`
- `target/cucumber-reports/cucumber.html`
- `target/cucumber-reports/cucumber.xml`

## Wybór przegladarki

Przegladarka jest ustawiana w hooku `@Before`:

- domyslnie: `chrome`
- override: `-Dbrowser=firefox`
