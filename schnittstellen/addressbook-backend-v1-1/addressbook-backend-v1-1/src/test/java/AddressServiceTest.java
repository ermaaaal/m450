package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address testAddress;

    @BeforeEach
    void setUp() {
        testAddress = new Address(1, "Hans", "Muster", "0790000000", new Date());
    }

    @Test
    void testSave() {
        when(addressRepository.save(testAddress)).thenReturn(testAddress);

        Address result = addressService.save(testAddress);

        assertNotNull(result);
        assertEquals("Muster", result.getLastname());
        verify(addressRepository, times(1)).save(testAddress);
    }

    @Test
    void testGetAll_Sorted() {
        Address a1 = new Address(1, "B", "Z", "1", new Date());
        Address a2 = new Address(2, "A", "A", "2", new Date());
        
        // Repo gibt unsortierte Liste zurück
        when(addressRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Address> result = addressService.getAll();

        // Service muss sortieren: A (Nachname A) vor Z (Nachname B)
        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getLastname());
        assertEquals("Z", result.get(1).getLastname());
    }

    @Test
    void testGetAddress() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(testAddress));

        Optional<Address> result = addressService.getAddress(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }
}