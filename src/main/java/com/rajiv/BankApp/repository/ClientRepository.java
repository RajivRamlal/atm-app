package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Integer>  {
}
