## Aufgabe 2: JUnit Zusammenfassung

### Gängige JUnit 5 Features

| Annotation / Feature | Beschreibung | Anwendungsfall |
| :--- | :--- | :--- |
| `@Test` | Markiert eine Methode als Testfall. | Die eigentliche Prüfung (z.B. `testCalculation`). |
| `@BeforeEach` | Wird **vor jedem** Test ausgeführt. | Initialisierung von Objekten (z.B. `new Calculator()`), Reset von DBs. |
| `@AfterEach` | Wird **nach jedem** Test ausgeführt. | Aufräumen von Ressourcen. |
| `@BeforeAll` | Wird **einmalig** vor allen Tests ausgeführt (muss static sein). | Teure Operationen wie Datenbankverbindung aufbauen. |
| `@DisplayName("...")` | Gibt dem Test einen lesbaren Namen im Report. | Bessere Lesbarkeit in den Logs ("Sollte Exception werfen wenn..."). |
| `assertEquals(exp, act)` | Prüft, ob erwartet und aktuell gleich sind. | Ergebnisprüfung: `assertEquals(4, 2+2)`. |
| `assertThrows(...)` | Prüft, ob eine bestimmte Exception geworfen wird. | Fehlerbehandlung testen (z.B. Division durch 0). |
| `assertTrue(...)` / `assertFalse(...)` | Prüft auf boolesche Wahrheitswerte. | Zustandsprüfungen: `assertTrue(list.isEmpty())`. |


## Aufgabe 3: Banken Simulation - Analyse

### Funktionsweise & Zusammenhänge

*   **Bank (Zentrale):**
    *   Verwaltet alle Konten in einer `TreeMap<String, Account>`.
    *   Erzeugt Konten (`create...`) und generiert IDs (z.B. "S-1000").
    *   Delegiert Aktionen wie `deposit`, `withdraw`, `print` anhand der ID an die entsprechenden Konten weiter.
    *   Berechnet die Gesamtbilanz der Bank.
*   **Account (Abstrakt):**
    *   Basisklasse für alle Kontotypen.
    *   Verwaltet `balance` (Saldo in Millirappen) und eine Liste von `bookings` (Transaktionshistorie).
    *   **Wichtig:** `canTransact(date)` stellt sicher, dass Buchungen zeitlich chronologisch erfolgen (Datum darf nicht kleiner sein als das der letzten Buchung).
*   **Booking:**
    *   Ein simpler Datensatz für eine Transaktion (Datum + Betrag).
*   **Subklassen (Polymorphie):**
    *   `SavingsAccount`: Standardkonto, keine Überziehung erlaubt (checkt Saldo vor `withdraw`).
    *   `SalaryAccount`: Hat ein `creditLimit` (negativer Wert), bis zu dem überzogen werden darf.
    *   `PromoYouthSavingsAccount`: Erbt von `SavingsAccount`, gibt aber bei jedem `deposit` 1% Bonus dazu.
*   **Utils & Comparators:**
    *   `BankUtils`: Formatierung von Datum (Tage seit 1970) und Währung.
    *   `AccountBalanceComparator`: Sortiert Konten nach Saldo für Top/Bottom Listen.