package io.gocklkatz.m12s.tsp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TspTest {

    @Test
    void solveTsp() {
        TspService service = new TspService();
        Tsp tsp = service.findOne(1);
        tsp.solve();

        assertEquals(List.of(1, 3, 2, 4), tsp.getTour());
        assertEquals(8, tsp.getCost());
    }
}