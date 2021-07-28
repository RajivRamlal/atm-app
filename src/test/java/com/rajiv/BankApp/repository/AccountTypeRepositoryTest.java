package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.AccountTypeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountTypeRepositoryTest {

    @Autowired
    AccountTypeRepository accountTypeRepository;

    @Test
    final void findByAccountTypeCode() {
        String code = "CHQ";
        AccountTypeEntity accountType = accountTypeRepository.findByAccountTypeCode(code);
        assertNotNull(accountType);

        assertTrue(accountType.getAccountTypeCode().equals(code));
    }
}