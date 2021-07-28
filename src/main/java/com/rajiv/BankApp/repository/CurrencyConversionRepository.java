package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.CurrencyConversionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConversionRepository extends CrudRepository<CurrencyConversionEntity, String> {

    CurrencyConversionEntity findByCurrencyCode(String code);
}
