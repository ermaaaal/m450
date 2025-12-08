package ch.schule.bank.junit5;

import ch.schule.Booking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Booking.
 */
public class BookingTests {
    
    // Hilfsvariablen um System.out.println zu testen
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Tests für die Erzeugung von Buchungen.
     */
    @Test
    public void testInitialization() {
        Booking booking = new Booking(100, 5000);
        
        assertEquals(100, booking.getDate());
        assertEquals(5000, booking.getAmount());
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        Booking booking = new Booking(12000, 2500);
        long currentBalance = 10000;
        
        booking.print(currentBalance);
        
        // Prüfen, ob etwas in die Konsole geschrieben wurde
        // Format ist Datum + Betrag + Saldo
        String output = outContent.toString();
        assertNotNull(output);
        assertTrue(output.length() > 0);
        // Da BankUtils verwendet wird, prüfen wir grob ob Zahlen enthalten sind
        assertTrue(output.contains("2'500.00")); // Betrag formatiert? (Annahme BankUtils Format)
    }
}