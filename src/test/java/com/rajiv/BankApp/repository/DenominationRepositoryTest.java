package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.DenominationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DenominationRepositoryTest {

    @Autowired
    DenominationRepository denominationRepository;

    @Test
    final void findByDenominationIdAndDenominationTypeCode() {
        int id = 5;
        char code = 'N';
        DenominationEntity denomination = denominationRepository.findByDenominationIdAndDenominationTypeCode(id, code);
        assertNotNull(denomination);

        assertEquals(id, denomination.getDenominationId());
        assertEquals(code, denomination.getDenominationTypeCode());
        assertEquals(200, denomination.getValue());
    }
}