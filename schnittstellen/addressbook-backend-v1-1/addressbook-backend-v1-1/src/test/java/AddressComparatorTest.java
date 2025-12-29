package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressComparatorTest {

    private AddressComparator comparator;
    private Address a1;
    private Address a2;
    private Address a3;

    @BeforeEach
    void setUp() {
        comparator = new AddressComparator();
        // Setup: Gleicher Nachname, unterschiedliche Vornamen
        a1 = new Address(1, "Albert", "Zweistein", "123", new Date());
        a2 = new Address(2, "Berta", "Zweistein", "456", new Date());
        // Setup: Anderer Nachname
        a3 = new Address(3, "Charlie", "Einstein", "789", new Date());
    }

    @Test
    void testCompare_DifferentLastnames() {
        // Einstein kommt vor Zweistein
        assertTrue(comparator.compare(a3, a1) < 0);
    }

    @Test
    void testCompare_SameLastnameDifferentFirstname() {
        // Albert kommt vor Berta (bei gleichem Nachnamen)
        assertTrue(comparator.compare(a1, a2) < 0);
    }

    @Test
    void testListSort() {
        List<Address> list = new ArrayList<>(List.of(a1, a2, a3));
        list.sort(comparator);

        assertEquals(a3, list.get(0)); // Einstein
        assertEquals(a1, list.get(1)); // Zweistein, Albert
        assertEquals(a2, list.get(2)); // Zweistein, Berta
    }
}