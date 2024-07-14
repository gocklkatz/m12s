package io.gocklkatz.m12s.tsp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TspTest {

    @Test
    void solveTsp() {
        TspService service = new TspService();
        Tsp tsp = service.findOne(1);
        tsp.solve();

        assertEquals("1324", tsp.getTour());
        assertEquals(8, tsp.getCost());
    }
}