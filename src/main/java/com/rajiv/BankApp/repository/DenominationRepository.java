package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.DenominationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenominationRepository extends CrudRepository<DenominationEntity, Integer> {

    DenominationEntity findByDenominationIdAndDenominationTypeCode(int denominationId, char denominationTypeCode);
}
