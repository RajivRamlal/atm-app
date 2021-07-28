package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.CreditCardLimitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardLimitRepository extends CrudRepository<CreditCardLimitEntity, String> {

    CreditCardLimitEntity findByClientAccountNumber(String accountNumber);
}
