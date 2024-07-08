package io.gocklkatz.m12s;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentProblemTest {

    /*
     * TC1: Best solution for this matrix [1, 0, 2, 3]
     * TC2: Non-square matrix
     */

    @Test
    void givenCertainMatrix_whenCallingSolve_shouldGiveKnownAnswer() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(List.of(9, 2, 7, 8));
        matrix.add(List.of(6, 4, 3, 7));
        matrix.add(List.of(5, 8, 1, 8));
        matrix.add(List.of(7, 6, 9, 4));

        AssignmentProblem ap = new AssignmentProblem(matrix);
        List<Integer> bestSolution = ap.solveAP();

        List<Integer> knownResult = new ArrayList<>(List.of(1, 0, 2, 3));
        assertEquals(bestSolution, knownResult);
    }
}