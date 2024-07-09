package io.gocklkatz.m12s;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentProblemTest {

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

    @Test
    void givenNonSquareMatrix_whenCallingSolve_shouldThrowIllegalArgumentException() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(List.of(9, 2));
        matrix.add(List.of(9, 2, 7));
        matrix.add(List.of(6, 4, 3));
        matrix.add(List.of(5, 8, 1));

        AssignmentProblem ap = new AssignmentProblem(matrix);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                ap::solveAP,
                "IllegalArgumentException to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Matrix must be square!"));
    }
}