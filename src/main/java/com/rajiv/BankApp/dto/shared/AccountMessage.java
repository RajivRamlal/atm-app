package com.rajiv.BankApp.dto.shared;

public enum AccountMessage {
    ACCOUNT_NOT_FOUND("No accounts to display"),
    CURRENCY_NOT_FOUND("Currency not found"),
    CURRENCY_CONVERSION_NOT_FOUND("Currency conversion for not found"),
    ATM_NOT_REGISTERED_OR_FUNDED("ATM not registered or unfunded"),
    INSUFFICIENT_FUNDS("Insufficient funds"),
    ATM_UPDATE_ERROR("Error updating Atm denominations"),
    CLIENT_BALANCE_UPDATE_ERROR("Error updating client balance");

    public final String message;

    AccountMessage(String message) {
        this.message = message;
    }
}
