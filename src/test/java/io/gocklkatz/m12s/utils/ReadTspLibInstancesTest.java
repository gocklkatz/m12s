package io.gocklkatz.m12s.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadTspLibInstancesTest {

    @Test
    void givenTspLibInstanceName_whenCallingReadTspLibInstances_ShouldReturnInstanceAsArray() {
        int[][] ret = ReadTspLibInstances.readTspLibInstance("br17.xml");
        assertEquals(0, ret.length);
    }
}