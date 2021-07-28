package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.AtmAllocationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface AtmAllocationRepository extends CrudRepository<AtmAllocationEntity, Integer> {

    List<AtmAllocationEntity> findByAtmId(int atmId);

    @Modifying
    @Query(value = "UPDATE atm_allocation atm SET atm.count = (atm.count - :cnt) WHERE atm.atm_id = :atmId AND atm.denomination_id = :denominationId", nativeQuery = true)
    Integer updateAtmAllocationByAtmId(@Param("atmId") int atmId,
                                       @Param("denominationId") int denominationId,
                                       @Param("cnt") int cnt);
}
