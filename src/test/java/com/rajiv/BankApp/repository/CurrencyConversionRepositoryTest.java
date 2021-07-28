package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.CurrencyConversionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CurrencyConversionRepositoryTest {

    @Autowired
    CurrencyConversionRepository currencyConversionRepository;

    @Test
    void findByCurrencyCode() {
        String code = "ZAR";
        CurrencyConversionEntity currency = currencyConversionRepository.findByCurrencyCode(code);
        assertNotNull(currency);

        assertTrue(currency.getCurrencyCode().equals(code));
    }
}