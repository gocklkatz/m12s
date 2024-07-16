package io.gocklkatz.m12s.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TspLibInstancesTest {

    @Test
    void givenTspLibInstanceName_whenCallingReadTspLibInstances_ShouldReturnInstanceAsArray() {

        TspLibInstances tspLibInstances = new TspLibInstances("br17.xml");
        double[][] ret = tspLibInstances.getTspLibInstanceAsArray();
        assertEquals(17, ret.length);
    }
}