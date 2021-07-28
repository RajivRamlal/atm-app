package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.AtmEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends CrudRepository<AtmEntity, Integer> {

    AtmEntity findByAtmId(int atmId);
}
