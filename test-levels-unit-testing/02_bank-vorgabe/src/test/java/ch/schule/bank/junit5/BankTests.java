package ch.schule.bank.junit5;

import ch.schule.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse 'Bank'.
 */
public class BankTests {

    private Bank bank;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Tests to create new Accounts
     */
    @Test
    public void testCreate() {
        String sId = bank.createSavingsAccount();
        assertNotNull(sId);
        assertTrue(sId.startsWith("S-"));

        String yId = bank.createPromoYouthSavingsAccount();
        assertNotNull(yId);
        assertTrue(yId.startsWith("Y-"));

        String pId = bank.createSalaryAccount(-5000);
        assertNotNull(pId);
        assertTrue(pId.startsWith("P-"));

        // Test ungültiges SalaryLimit
        assertNull(bank.createSalaryAccount(100));
    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        String id = bank.createSavingsAccount();
        
        // Gültig
        assertTrue(bank.deposit(id, 100, 500));
        assertEquals(500, bank.getBalance(id));
        
        // Ungültige ID
        assertFalse(bank.deposit("gibt-es-nicht", 100, 500));
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        String id = bank.createSavingsAccount();
        bank.deposit(id, 100, 1000);
        
        // Gültig
        assertTrue(bank.withdraw(id, 101, 500));
        assertEquals(500, bank.getBalance(id));
        
        // Ungültige ID
        assertFalse(bank.withdraw("gibt-es-nicht", 101, 500));
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        String id = bank.createSavingsAccount();
        bank.print(id);
        
        String output = outContent.toString();
        assertTrue(output.contains(id));
        
        // Ungültige ID sollte keinen Fehler werfen (aber nichts finden)
        bank.print("invalid");
    }

    /**
     * Experimente mit print(year, month).
     */
    @Test
    public void testMonthlyPrint() {
        String id = bank.createSavingsAccount();
        bank.print(id, 2020, 1);
        
        String output = outContent.toString();
        assertTrue(output.contains(id));
    }

    /**
     * Testet den Gesamtkontostand der Bank.
     */
    @Test
    public void testBalance() {
        String id1 = bank.createSavingsAccount();
        String id2 = bank.createSavingsAccount();
        
        bank.deposit(id1, 100, 1000);
        bank.deposit(id2, 100, 2000);
        
        // Laut Bank.java Logik: balance -= account.getBalance()
        // Einlagen der Kunden sind Verbindlichkeiten der Bank -> Negativ
        assertEquals(-3000, bank.getBalance());
        
        // Einzelkonto Balance
        assertEquals(1000, bank.getBalance(id1));
        assertEquals(0, bank.getBalance("invalid")); // Soll 0 sein
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testTop5() {
        String id1 = bank.createSavingsAccount();
        String id2 = bank.createSavingsAccount();
        bank.deposit(id1, 100, 5000);
        bank.deposit(id2, 100, 1000);
        
        bank.printTop5();
        
        String output = outContent.toString();
        // id1 hat mehr Geld, sollte also vor id2 kommen oder zumindest beide enthalten sein
        assertTrue(output.contains(id1));
        assertTrue(output.contains(id2));
    }

    /**
     * Tested die Ausgabe der "bottom 5" konten.
     */
    @Test
    public void testBottom5() {
        String id1 = bank.createSavingsAccount();
        bank.deposit(id1, 100, 5000);
        
        bank.printBottom5();
        
        String output = outContent.toString();
        assertTrue(output.contains(id1));
    }
}