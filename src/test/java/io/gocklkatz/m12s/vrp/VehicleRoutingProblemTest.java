package io.gocklkatz.m12s.vrp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRoutingProblemTest {
    //https://www.youtube.com/watch?v=A1wsIFDKqBk&t=362s
    List<List<Integer>> matrix = new ArrayList<>();
    {
        matrix.add(List.of(0,  20, 18, 14, 16, 12, 19));
        matrix.add(List.of(20,  0, 22, 18, 30, 26, 28));
        matrix.add(List.of(18, 22,  0, 32, 20, 22, 21));
        matrix.add(List.of(14, 18, 32,  0, 20, 22, 21));
        matrix.add(List.of(16, 30, 20, 20,  0, 30, 22));
        matrix.add(List.of(12, 26, 22, 22, 30,  0, 26));
        matrix.add(List.of(19, 28, 21, 21, 22, 26,  0));
    }

    @Test
    void givenCertainMatrix_whenCallingSingleSolution_NoCapacityConstraint_shouldGiveKnownAnswer() {
        VehicleRoutingProblem vrp = new VehicleRoutingProblem(matrix);
        Solution solution = vrp.singleSolutionNoCapacityConstraint();

        assertEquals(List.of(3, 4, 5), solution.getRoutes().getFirst().stops());
        assertEquals(List.of(6, 1, 2), solution.getRoutes().get(1).stops());
        assertEquals(163, solution.getObjectiveFunctionValue());
    }

    @Test
    void givenCertainMatrix_whenCallingSolveCompleteEnumeration_NoCapacityConstraint_shouldGiveKnownAnswer() {
        VehicleRoutingProblem vrp = new VehicleRoutingProblem(matrix);
        Solution solution = vrp.solveCompleteEnumerationNoCapacityConstraint();

        assertEquals(List.of(5), solution.getRoutes().getFirst().stops());
        assertEquals(List.of(3, 1, 2, 6, 4), solution.getRoutes().get(1).stops());
        assertEquals(137, solution.getObjectiveFunctionValue());
    }

    @Test
    void solveCompleteEnumerationNoCapacityConstraintOptimalTruckNumberTest() {
        VehicleRoutingProblem vrp = new VehicleRoutingProblem(matrix);
        Solution solution = vrp.solveCompleteEnumerationNoCapacityConstraintOptimalTruckNumber();

        assertEquals(List.of(4, 6, 3, 1, 2, 5), solution.getRoutes().getFirst().stops());
        assertEquals(133, solution.getObjectiveFunctionValue());
    }

}