package com.rubix.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rubix.constant.TestCubeConstants;
import com.rubix.entity.CubeEntity;
import com.rubix.repository.CubeRepositoryTest;

public class CubeRepositoryImplTest extends CubeRepositoryTest {

    @Autowired
    private CubeRepositoryImpl cubeRepository;

    @Test
    public void testKeyFound() {
        String currentKey = TestCubeConstants.EXISTING[0];
        final CubeEntity found = cubeRepository.findByKey(currentKey, TestCubeConstants.COLLECTION);
        assertNotNull("Entry should already exist but was not found: key= " + currentKey, found);
        assertEquals("Keys dont match - expected: " + currentKey + " actual: " + found.getKey(), currentKey,
                found.getKey());
    }

    @Test
    public void testKeyNotFound() {
        String currentKey = TestCubeConstants.NON_EXISTING[0];
        final CubeEntity found = cubeRepository.findByKey(currentKey, TestCubeConstants.COLLECTION);
        assertNull("Entry should not exist but was found: key= " + currentKey, found);
    }
}
