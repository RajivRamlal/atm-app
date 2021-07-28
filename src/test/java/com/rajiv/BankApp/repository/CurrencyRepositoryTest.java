package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.CurrencyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CurrencyRepositoryTest {

    @Autowired
    CurrencyRepository currencyRepository;

    @Test
    final void findByCurrencyCode() {
        String code = "THB";
        CurrencyEntity currency = currencyRepository.findByCurrencyCode(code);
        assertNotNull(currency);

        assertTrue(currency.getCurrencyCode().equals(code));
        assertEquals("THAI BAHT", currency.getDescription().toUpperCase());
    }
}