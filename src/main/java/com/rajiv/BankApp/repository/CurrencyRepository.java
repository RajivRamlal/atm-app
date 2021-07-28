package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyEntity, String> {

    CurrencyEntity findByCurrencyCode(String code);
}
