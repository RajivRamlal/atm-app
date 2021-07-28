package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.DenominationTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenominationTypeRepository extends CrudRepository<DenominationTypeEntity, String> {
}
