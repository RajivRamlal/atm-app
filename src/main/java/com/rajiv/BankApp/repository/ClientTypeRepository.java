package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.ClientTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTypeRepository extends CrudRepository<ClientTypeEntity, String> {
}
