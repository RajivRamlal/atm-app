package com.rajiv.BankApp.controller;

import com.rajiv.BankApp.dto.AccountDto;
import com.rajiv.BankApp.dto.CurrencyAccountDto;
import com.rajiv.BankApp.dto.DispenseDto;
import com.rajiv.BankApp.request.WithdrawModel;
import com.rajiv.BankApp.response.AccountRest;
import com.rajiv.BankApp.response.CurrencyAccountRest;
import com.rajiv.BankApp.response.DispenseRest;
import com.rajiv.BankApp.response.WithdrawRest;
import com.rajiv.BankApp.service.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Type;
import java.util.List;

@Controller
public class BankAppController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/balance/{id}")
    public String getBalance(@PathVariable int id, Model model) {

        List<AccountRest> returnValue;
        List<AccountDto> accounts = accountService.getAccountByClientId(id);

        Type listType = new TypeToken<List<AccountRest>>() {
        }.getType();

        returnValue = new ModelMapper().map(accounts, listType);
        model.addAttribute("accounts", returnValue);

        return "list-accounts";
    }

    @GetMapping(path = "balance/currency/{id}")
    public String getCurrencyBalance(@PathVariable("id") int id, Model model) {

        List<CurrencyAccountRest> returnValue;
        List<CurrencyAccountDto> accounts = accountService.getCurrencyAccountByClientId(id);

        Type listType = new TypeToken<List<CurrencyAccountRest>>() {
        }.getType();

        returnValue = new ModelMapper().map(accounts, listType);
        model.addAttribute("currencyAccounts", returnValue);

        return "list-currency-accounts";
    }

    @GetMapping(path = "/withdraw/{atmId}/{clientId}/{accountNumber}")
    public String getWithdrawal(@PathVariable("atmId") int atmId,
                                @PathVariable("clientId") int clientId,
                                @PathVariable("accountNumber") String accountNumber,
                                Model model) {

        WithdrawRest returnValue;
        AccountDto account = accountService.getAccountWithdrawal(atmId, clientId, accountNumber);

        Type listType = new TypeToken<WithdrawRest>() {
        }.getType();

        returnValue = new ModelMapper().map(account, listType);
        model.addAttribute("withdraw", returnValue);
        model.addAttribute("withdrawModel", new WithdrawModel());

        return "withdraw-account";
    }

    @GetMapping(path = "/withdraw/account/{atmId}/{accountNumber}/{clientId}")
    public String getWithdrawAmount(@ModelAttribute WithdrawModel withdrawModel,
                                    Model model) {

        List<DispenseRest> returnValue;
        List<DispenseDto> withdrawal = accountService.getWithdrawalByAmount(withdrawModel.getAtmId(), withdrawModel.getAccountNumber(), withdrawModel.getAmount());

        Type listType = new TypeToken<List<DispenseRest>>() {
        }.getType();

        returnValue = new ModelMapper().map(withdrawal, listType);
        model.addAttribute("withdrawalDetails", withdrawModel);
        model.addAttribute("withdrawal", returnValue);

        return "withdraw-confirm";
    }

    @PostMapping(path = "/withdraw/{atmId}/{clientId}/{accountNumber}/{amount}")
    public String updateWithdrawal(@ModelAttribute WithdrawModel withdrawModel,
                                   Model model) {

        List<DispenseRest> returnValue;
        List<DispenseDto> withdrawal = accountService.getWithdrawalByAmount(withdrawModel.getAtmId(), withdrawModel.getAccountNumber(), withdrawModel.getAmount());

        Type listType = new TypeToken<List<DispenseRest>>() {
        }.getType();

        returnValue = new ModelMapper().map(withdrawal, listType);
        model.addAttribute("withdrawal", returnValue);

        accountService.saveTransaction(withdrawModel);

        return "withdraw-dispense";
    }

    @GetMapping(path = "/cancel")
    public String cancelWithdrawal() {

        return "withdraw-cancel";
    }
}
