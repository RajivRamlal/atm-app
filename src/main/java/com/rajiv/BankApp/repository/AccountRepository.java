package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.AccountEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, String> {

    List<AccountEntity> findByClientId(int id);
    AccountEntity findByClientAccountNumberAndClientId(String accountNumber, int id);
    AccountEntity findByClientAccountNumber(String accountNumber);

    @Modifying
    @Query("UPDATE client_account SET display_balance = (display_balance - :amount) WHERE client_account_number = :accountNumber")
    Integer updateBalanceByAccountNumber(@Param("accountNumber") String accountNumber,
                                         @Param("amount") double amount);
}
