package test;

import main.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    @Test
    @DisplayName("Test Addition")
    void testAdd() {
        assertEquals(5.0, calc.add(2.0, 3.0), "2 + 3 sollte 5 sein");
    }

    @Test
    @DisplayName("Test Subtraktion")
    void testSub() {
        assertEquals(1.0, calc.sub(3.0, 2.0));
    }

    @Test
    @DisplayName("Test Multiplikation")
    void testMult() {
        assertEquals(6.0, calc.mult(2.0, 3.0));
    }

    @Test
    @DisplayName("Test Division")
    void testDiv() {
        assertEquals(2.0, calc.div(6.0, 3.0));
    }

    @Test
    @DisplayName("Test Division durch Null")
    void testDivZero() {
        assertThrows(ArithmeticException.class, () -> calc.div(1.0, 0.0));
    }
}