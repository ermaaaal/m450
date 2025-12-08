package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests der Klasse SalaryAccount.
 */
public class SalaryAccountTests {

    private SalaryAccount account;

    @BeforeEach
    public void setUp() {
        // Lohnkonto mit Kreditlimite von -1000
        account = new SalaryAccount("P-1", -1000);
    }

    @Test
    public void test() {
        // Kontostand 0
        
        // 1. Abheben ins Minus (erlaubt bis Limit)
        assertTrue(account.withdraw(100, 500));
        assertEquals(-500, account.getBalance());

        // 2. Weiter abheben bis genau ans Limit
        assertTrue(account.withdraw(101, 500));
        assertEquals(-1000, account.getBalance());

        // 3. Versuch Limit zu sprengen (nicht erlaubt)
        assertFalse(account.withdraw(102, 1));
        assertEquals(-1000, account.getBalance());
        
        // 4. Einzahlen bringt Saldo hoch
        assertTrue(account.deposit(103, 2000));
        assertEquals(1000, account.getBalance());
    }
}