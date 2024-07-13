package io.gocklkatz.m12s.assignment;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentProblemTest {

    @Test
    void givenCertainMatrix_whenCallingSolveFullEnum_shouldGiveKnownAnswer() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(List.of(9, 2, 7, 8));
        matrix.add(List.of(6, 4, 3, 7));
        matrix.add(List.of(5, 8, 1, 8));
        matrix.add(List.of(7, 6, 9, 4));

        AssignmentProblem ap = new AssignmentProblem(matrix);
        Result<Integer> bestResult = ap.solveApFullEnumeration();

        Result<Integer> knownResult = new Result<>(List.of(1, 0, 2, 3), 13);
        assertEquals(knownResult, bestResult);
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
                ap::solveApFullEnumeration,
                "IllegalArgumentException to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Matrix must be square!"));
    }

    @Test
    void givenCertainMatrix_whenCallingSolveGreedy_shouldGiveKnownAnswer() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(List.of(9, 2, 7, 8));
        matrix.add(List.of(6, 4, 3, 7));
        matrix.add(List.of(5, 8, 1, 8));
        matrix.add(List.of(7, 6, 9, 4));

        AssignmentProblem ap = new AssignmentProblem(matrix);
        Result<Integer> bestResult = ap.solveApGreedyHeuristic();

        Result<Integer> knownResult = new Result<>(List.of(1, 2, 0, 3), 14);
        assertEquals(knownResult, bestResult);
    }

    /*
    @Test
    void maximumInputSizeTest() {
        List<List<Integer>> matrix = Combinatorics.generateApSample(10);
        AssignmentProblem ap = new AssignmentProblem(matrix);
        Result<Integer> bestSolution = ap.solveApFullEnumeration();
        System.out.println(bestSolution);
    }
     */
}