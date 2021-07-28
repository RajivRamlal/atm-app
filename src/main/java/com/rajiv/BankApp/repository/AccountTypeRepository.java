package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.AccountTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountTypeEntity, String> {

    AccountTypeEntity findByAccountTypeCode(String code);
}
