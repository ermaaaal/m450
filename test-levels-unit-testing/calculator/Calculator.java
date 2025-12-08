# Lösungen: Testing & Bank Simulation

## Aufgabe 1: Simpler Rechner

Hier ist die Implementierung der Klasse und der dazugehörige JUnit 5 Test.

### Calculator.java (src/main/java/...)
```java
package main;

public class Calculator {
    public double add(double a, double b) { return a + b; }
    public double sub(double a, double b) { return a - b; }
    public double mult(double a, double b) { return a * b; }
    public double div(double a, double b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }
}