package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für das Promo-Jugend-Sparkonto.
 */
public class PromoYouthSavingsAccountTests {

    private PromoYouthSavingsAccount account;

    @BeforeEach
    public void setUp() {
        account = new PromoYouthSavingsAccount("Y-1");
    }

    @Test
    public void test() {
        // Promo: 1% Bonus bei Einzahlung
        
        // Einzahlung 100 -> 1 Bonus -> Saldo 101
        assertTrue(account.deposit(100, 100));
        assertEquals(101, account.getBalance());

        // Einzahlung 200 -> 2 Bonus -> Total Einzahlung 202 -> Saldo 303
        assertTrue(account.deposit(101, 200));
        assertEquals(303, account.getBalance());
        
        // Abheben hat KEINEN Bonus (Verhalten wie SavingsAccount)
        assertTrue(account.withdraw(102, 100));
        assertEquals(203, account.getBalance());
    }
}