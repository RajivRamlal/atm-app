package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.ClientSubTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientSubTypeRepository extends CrudRepository<ClientSubTypeEntity, String> {
}
