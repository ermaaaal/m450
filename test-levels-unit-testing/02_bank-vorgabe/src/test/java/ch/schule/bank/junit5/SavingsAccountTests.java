package ch.schule.bank.junit5;

import ch.schule.SavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse SavingsAccount.
 */
public class SavingsAccountTests {

    private SavingsAccount account;

    @BeforeEach
    public void setUp() {
        account = new SavingsAccount("S-1");
    }

    @Test
    public void test() {
        account.deposit(100, 1000);

        // 1. Normales Abheben (erlaubt)
        assertTrue(account.withdraw(101, 500));
        assertEquals(500, account.getBalance());

        // 2. Versuch Konto zu überziehen (nicht erlaubt bei Savings)
        assertFalse(account.withdraw(102, 600));
        assertEquals(500, account.getBalance()); // Saldo muss unverändert sein

        // 3. Alles abheben (erlaubt)
        assertTrue(account.withdraw(103, 500));
        assertEquals(0, account.getBalance());
    }
}