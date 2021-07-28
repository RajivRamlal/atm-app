package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.CreditCardLimitEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CreditCardLimitRepositoryTest {

    @Autowired
    CreditCardLimitRepository creditCardLimitRepository;

    @Test
    final void findByClientAccountNumber() {
        String code = "5028920923";
        CreditCardLimitEntity creditLimit = creditCardLimitRepository.findByClientAccountNumber(code);
        assertNotNull(creditLimit);

        assertTrue(creditLimit.getClientAccountNumber().equals(code));
    }
}