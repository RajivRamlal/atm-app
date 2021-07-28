package com.rajiv.BankApp.controller;

import com.rajiv.BankApp.dto.AccountDto;
import com.rajiv.BankApp.dto.shared.AccountCurrency;
import com.rajiv.BankApp.dto.shared.AccountType;
import com.rajiv.BankApp.request.WithdrawModel;
import com.rajiv.BankApp.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BankAppControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private BankAppController bankAppController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        Mockito.reset(accountService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bankAppController).build();
    }

    @Test
    final void getBalance() throws Exception {
        this.mockMvc.perform(get("/balance/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("list-accounts"))
                .andExpect(model().attributeExists("accounts"))
                .andExpect(model().hasNoErrors());
    }

    @Test
    final void getCurrencyBalance() throws Exception {
        this.mockMvc.perform(get("/balance/currency/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(view().name("list-currency-accounts"))
                .andExpect(model().attributeExists("currencyAccounts"))
                .andExpect(model().hasNoErrors());
    }

    @Test
    final void getWithdrawal() throws Exception {
        AccountDto account = new AccountDto();

        account.setClientAccountNumber("1053664521");
        account.setClientId(1);
        account.setAccountTypeCode(AccountType.SAVINGS.accType);
        account.setAccountType("Savings Account");
        account.setCurrencyCode(AccountCurrency.SOUTH_AFRICAN_RAND.currency);
        account.setDisplayBalance(1750);
        account.setAtmId(1);

        when(accountService.getAccountWithdrawal(anyInt(), anyInt(), anyString())).thenReturn(account);

        this.mockMvc.perform(get("/withdraw/{atmId}/{clientId}/{accountNumber}", 1, 1, "1053664521"))
                .andExpect(status().isOk())
                .andExpect(view().name("withdraw-account"))
                .andExpect(model().attributeExists("withdraw"))
                .andExpect(model().attributeExists("withdrawModel"))
                .andExpect(model().hasNoErrors());

        verify(accountService, times(1)).getAccountWithdrawal(1, 1, "1053664521");
        verifyNoMoreInteractions(accountService);
    }

    @Test
    final void getWithdrawAmount() throws Exception {
        WithdrawModel withdrawModel = new WithdrawModel();

        withdrawModel.setAtmId(1);
        withdrawModel.setAccountNumber("1053664521");
        withdrawModel.setAmount(157.48);
        withdrawModel.setClientId(1);

        this.mockMvc.perform(get("/withdraw/account/1/1053664521/1", withdrawModel))
                .andExpect(view().name("withdraw-confirm"))
                .andExpect(model().attributeExists("withdrawalDetails"))
                .andExpect(model().attributeExists("withdrawal"))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());
    }

    @Test
    final void updateWithdrawal() throws Exception {
        this.mockMvc.perform(post("/withdraw/{atmId}/{clientId}/{accountNumber}/{amount}", 3, 1, "1011426755", 147.50))
                .andExpect(status().isOk())
                .andExpect(view().name("withdraw-dispense"))
                .andExpect(model().attributeExists("withdrawal"))
                .andExpect(model().hasNoErrors());
    }

    @Test
    final void cancelWithdrawal() throws Exception {
        this.mockMvc.perform(get("/cancel"))
                .andExpect(status().isOk())
                .andExpect(view().name("withdraw-cancel"));
    }
}