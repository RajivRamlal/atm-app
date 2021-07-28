package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.AtmEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AtmRepositoryTest {

    @Autowired
    AtmRepository atmRepository;

    @Test
    final void findByAtmId() {
        int id = 1;
        AtmEntity atm = atmRepository.findByAtmId(id);
        assertNotNull(atm);

        assertEquals(id, atm.getAtmId());
        assertNotEquals("ESTGATE3", atm.getName().toUpperCase());
    }
}