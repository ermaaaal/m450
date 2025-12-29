package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Autowired
    private ObjectMapper objectMapper;

    private Address testAddress;

    @BeforeEach
    void setUp() {
        testAddress = new Address(1, "Test", "User", "12345", new Date());
    }

    @Test
    void createAddress() throws Exception {
        given(addressService.save(any(Address.class))).willReturn(testAddress);

        mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testAddress)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.lastname").value("User"));
    }

    @Test
    void getAddresses() throws Exception {
        given(addressService.getAll()).willReturn(Collections.singletonList(testAddress));

        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("Test"));
    }

    @Test
    void getAddress_Found() throws Exception {
        given(addressService.getAddress(1)).willReturn(Optional.of(testAddress));

        mockMvc.perform(get("/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getAddress_NotFound() throws Exception {
        given(addressService.getAddress(99)).willReturn(Optional.empty());

        mockMvc.perform(get("/address/99"))
                .andExpect(status().isNotFound());
    }
}