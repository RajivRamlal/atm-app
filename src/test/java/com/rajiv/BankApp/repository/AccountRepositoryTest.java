package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.AccountEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    final void findByClientId() {
        int clientId = 1;

        List<AccountEntity> account = accountRepository.findByClientId(clientId);
        assertNotNull(account);
    }

    @Test
    final void findByClientAccountNumberAndClientId() {
        String accountNumber = "1053664521";
        int clientId = 1;

        AccountEntity account = accountRepository.findByClientAccountNumberAndClientId(accountNumber, clientId);
        assertNotNull(account);

        assertEquals(clientId, account.getClientId());
    }

    @Test
    final void findByClientAccountNumber() {
        String accountNumber = "1053664521";

        AccountEntity account = accountRepository.findByClientAccountNumber(accountNumber);
        assertNotNull(account);

        assertEquals(accountNumber, account.getClientAccountNumber());
    }

    @Test
    final void updateBalanceByAccountNumber() {
        String accountNumber = "1053664521";
        double amount = 1000;

        AccountEntity account = accountRepository.findByClientAccountNumber(accountNumber);
        assertNotNull(account);

        int updatedRows = accountRepository.updateBalanceByAccountNumber(accountNumber, amount);
        assertNotEquals(0, updatedRows);

        AccountEntity updatedAccount = accountRepository.findByClientAccountNumber(accountNumber);
        assertNotNull(updatedAccount);

        assertEquals(updatedAccount.getDisplayBalance(), (account.getDisplayBalance() - amount));
    }
}