package io.gocklkatz.m12s.vrp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRoutingProblemTest {

    @Test
    void givenCertainMatrix_whenCallingSingleSolution_NoCapacityConstraint_shouldGiveKnownAnswer() {
        //https://www.youtube.com/watch?v=A1wsIFDKqBk&t=362s
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(List.of(0,  20, 18, 14, 16, 12, 19));
        matrix.add(List.of(20,  0, 22, 18, 30, 26, 28));
        matrix.add(List.of(18, 22,  0, 32, 20, 22, 21));
        matrix.add(List.of(14, 18, 32,  0, 20, 22, 21));
        matrix.add(List.of(16, 30, 20, 20,  0, 30, 22));
        matrix.add(List.of(12, 26, 22, 22, 30,  0, 26));
        matrix.add(List.of(19, 28, 21, 21, 22, 26,  0));

        VehicleRoutingProblem vrp = new VehicleRoutingProblem(matrix);
        Solution solution = vrp.singleSolutionNoCapacityConstraint();

        assertEquals(solution.getObjectiveFunctionValue(), 163);
    }

    @Test
    void givenCertainMatrix_whenCallingSolveCompleteEnumeration_NoCapacityConstraint_shouldGiveKnownAnswer() {
        //https://www.youtube.com/watch?v=A1wsIFDKqBk&t=362s
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(List.of(0,  20, 18, 14, 16, 12, 19));
        matrix.add(List.of(20,  0, 22, 18, 30, 26, 28));
        matrix.add(List.of(18, 22,  0, 32, 20, 22, 21));
        matrix.add(List.of(14, 18, 32,  0, 20, 22, 21));
        matrix.add(List.of(16, 30, 20, 20,  0, 30, 22));
        matrix.add(List.of(12, 26, 22, 22, 30,  0, 26));
        matrix.add(List.of(19, 28, 21, 21, 22, 26,  0));

        VehicleRoutingProblem vrp = new VehicleRoutingProblem(matrix);
        Solution solution = vrp.solveCompleteEnumerationNoCapacityConstraint();

        assertEquals(List.of(5), solution.getRoute1());
        assertEquals(List.of(3, 1, 2, 6, 4), solution.getRoute2());
        assertEquals(137, solution.getObjectiveFunctionValue());
    }

}