package com.rajiv.BankApp.repository;

import com.rajiv.BankApp.model.AtmAllocationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AtmAllocationRepositoryTest {

    @Autowired
    AtmAllocationRepository atmAllocationRepository;

    @Test
    final void updateAtmAllocationByAtmId() {
        int id = 1;
        int denominationId = 1;
        int count = 5;

        List<AtmAllocationEntity> atmAllocation = atmAllocationRepository.findByAtmId(id);
        assertNotNull(atmAllocation);

        OptionalInt allocation = atmAllocation.stream()
                .filter(e -> e.getDenominationId() == denominationId)
                .mapToInt(e -> e.getCount())
                .findAny();

        int updatedRows = atmAllocationRepository.updateAtmAllocationByAtmId(id, denominationId, count);
        assertNotEquals(0, updatedRows);

        List<AtmAllocationEntity> updatedAtmAllocation = atmAllocationRepository.findByAtmId(id);
        assertNotNull(updatedAtmAllocation);

        OptionalInt updatedAllocation = updatedAtmAllocation.stream()
                .filter(e -> e.getDenominationId() == denominationId)
                .mapToInt(e -> e.getCount())
                .findAny();

        assertEquals(updatedAllocation.getAsInt(), (allocation.getAsInt() - count));
    }
}