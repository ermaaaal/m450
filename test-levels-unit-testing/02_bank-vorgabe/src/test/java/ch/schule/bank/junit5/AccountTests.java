package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.Booking;
import ch.schule.SavingsAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Account.
 */
public class AccountTests {

    private Account account;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Wir nutzen SavingsAccount, da Account abstrakt ist
        account = new SavingsAccount("TEST-ID");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Tested die Initialisierung eines Kontos.
     */
    @Test
    public void testInit() {
        assertEquals("TEST-ID", account.getId());
        assertEquals(0, account.getBalance());
    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        // 1. Erfolgreiche Einzahlung
        assertTrue(account.deposit(100, 5000));
        assertEquals(5000, account.getBalance());

        // 2. Negativer Betrag (soll scheitern)
        assertFalse(account.deposit(101, -100));
        assertEquals(5000, account.getBalance());

        // 3. Falsches Datum (Vergangenheit)
        assertFalse(account.deposit(99, 1000));
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        account.deposit(100, 5000);

        // 1. Erfolgreiches Abheben
        assertTrue(account.withdraw(101, 2000));
        assertEquals(3000, account.getBalance());

        // 2. Negativer Betrag
        assertFalse(account.withdraw(102, -500));
        
        // 3. Chronologie Fehler
        assertFalse(account.withdraw(99, 100));
    }

    /**
     * Tests the reference from SavingsAccount
     */
    @Test
    public void testReferences() {
        // Testet getter/setter für booking property (aus dem Code ersichtlich)
        Booking b = new Booking(1, 100);
        account.setBooking(b);
        assertEquals(b, account.getBooking());
    }

    /**
     * teste the canTransact Flag
     */
    @Test
    public void testCanTransact() {
        // Noch keine Buchung -> True
        assertTrue(account.canTransact(10));
        
        account.deposit(100, 1000); // Buchung am Tag 100
        
        // Tag 99 -> False
        assertFalse(account.canTransact(99));
        // Tag 100 -> True (gleicher Tag erlaubt)
        assertTrue(account.canTransact(100));
        // Tag 101 -> True
        assertTrue(account.canTransact(101));
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        account.deposit(100, 1000);
        account.print();
        
        String output = outContent.toString();
        assertTrue(output.contains("Kontoauszug"));
        assertTrue(output.contains("TEST-ID"));
    }

    /**
     * Experimente mit print(year,month).
     */
    @Test
    public void testMonthlyPrint() {
        // Datum berechnen: 1970 + Tage. 
        // Wir nehmen einfachheitshalber Tag 0 (Januar 1970)
        account.deposit(10, 1000); 
        
        // Drucke Januar 1970
        account.print(1970, 1);
        
        String output = outContent.toString();
        assertTrue(output.contains("Monat: 1.1970"));
    }
}