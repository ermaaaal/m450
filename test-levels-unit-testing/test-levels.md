# Zusammenfassung der Testerkenntnisse

## Aufgabe 1: Teststatus in der Firma (Beispielhaft)

**Was wird wie getestet?**
*   **Unit Tests:** Automatisiert durch Entwickler (z.B. JUnit, Jest).
*   **Integration Tests:** API-Schnittstellen und Datenbankanbindungen (automatisiert).
*   **E2E / UI Tests:** Kritische User Flows im Browser (z.B. Cypress/Selenium).
*   **Manuelle Tests:** Exploratives Testing neuer Features vor dem Release.

**Genutzte Test Levels**
*   Unit Testing (Komponententests)
*   Integration Testing
*   System Testing (End-to-End)
*   User Acceptance Testing (UAT)

**Zeitpunkt der Testausführung**
*   **Lokal:** Während der Entwicklung.
*   **CI/CD Pipeline:** Bei jedem Commit/Push (Unit & Integration).
*   **Nightly Builds:** Aufwändige Regression-Tests.
*   **Vor Release:** Manuelle Abnahme (UAT).

**QA Teams & Organisation**
*   *Struktur:* Cross-funktionale Teams (Devs schreiben Tests selbst).
*   *Support:* Ein kleines dediziertes QA-Team kümmert sich um Test-Infrastruktur und komplexe E2E-Szenarien.

**Testing Life Cycle**
1.  **Analyse:** Akzeptanzkriterien in der User Story definieren.
2.  **Design:** Testfälle ableiten (TDD/BDD Ansatz).
3.  **Implementierung:** Schreiben der automatisierten Tests.
4.  **Ausführung:** Pipeline-Run & Reporting.
5.  **Wartung:** Anpassung von Tests bei Code-Änderungen.

---

## Aufgabe 2: Einordnung & Abhängigkeiten

Hier ist die logische Hierarchie von abstrakt (Strategie) zu konkret (Handlung):

### 1. Testing Approach (Die Strategie)
*   **Definition:** Die übergeordnete Philosophie (z.B. Agile Testing, Shift-Left, Risk-based Testing).
*   **Abhängigkeit:** Bestimmt, *wann* und *wie intensiv* die darunterliegenden Levels und Typen angewendet werden.

### 2. Testing Levels (Die Ebene / Der Ort)
*   **Definition:** Wo im System wird getestet? (Unit -> Integration -> System -> Acceptance).
*   **Abhängigkeit:** Strukturiert den Testprozess entlang der Architektur. Ein "Approach" definiert oft die Pyramide (viele Unit-Tests, wenige System-Tests).

### 3. Testing Types (Das Ziel / Das "Was")
*   **Definition:** Welches Qualitätsmerkmal wird geprüft? (Funktional, Performance, Security, Usability).
*   **Abhängigkeit:** Diese Typen können auf verschiedenen *Levels* angewendet werden (z.B. Performance-Tests auf Unit-Ebene oder System-Ebene).

### 4. Testing Techniques & Tactics (Die Methode / Das "Wie")
*   **Definition:** Konkrete Verfahren zur Testfallerstellung (Blackbox, Whitebox, Äquivalenzklassenbildung, Grenzwertanalyse).
*   **Abhängigkeit:** Dies sind die Werkzeuge, um einen *Testing Type* auf einem bestimmten *Testing Level* effektiv umzusetzen.

### Zusammenfassung der Abhängigkeit:
> Der **Approach** gibt den Rahmen vor. Innerhalb dieses Rahmens durchlaufen wir verschiedene **Levels**. Auf jedem Level führen wir spezifische **Types** (Art der Tests) durch und nutzen dafür konkrete **Techniques** (Methoden), um die Testfälle zu designen.