package io.gocklkatz.m12s.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

// https://github.com/eugenp/tutorials/blob/afdc50e4d59306574a48a3470abb3b262d6abba0/algorithms-modules/algorithms-miscellaneous-5/src/test/java/com/baeldung/algorithms/combinatorics/CombinatoricsUnitTest.java
class CombinatoricHelperTest {

    @Test
    void givenEmptySequence_whenCallingPermutations_ShouldReturnEmptyList() {
        List<Integer> sequence = List.of();

        List<List<Integer>> permutations = CombinatoricHelper.permutations(sequence);

        assertEquals(0, permutations.size());
    }

    @Test
    void givenOneElementSequence_whenCallingPermutations_ShouldReturnPermutations() {
        List<Integer> sequence = List.of(1);

        List<List<Integer>> permutations = CombinatoricHelper.permutations(sequence);

        assertEquals(1, permutations.size());
        assertEquals(1, permutations.getFirst().size());
        assertSame(1, permutations.getFirst().getFirst());
    }

    @Test
    void givenFourElementsSequence_whenCallingPermutations_ShouldReturnPermutations() {
        List<Integer> sequence = Arrays.asList(1, 2, 3, 4);

        List<List<Integer>> permutations = CombinatoricHelper.permutations(sequence);

        assertEquals(24, permutations.size());
        assertEquals(24, new HashSet<>(permutations).size());
    }

    @Test
    void givenThreeElementsSequence_whenCallingPowerSet_ShouldReturnEightEntries() {
        List<Character> sequence = Arrays.asList('a', 'b', 'c');

        List<List<Character>> powerSet = CombinatoricHelper.generatePowerSet(sequence);

        assertEquals(8, powerSet.size());
    }

    @Test
    void givenN_whenCallingGenerateSample_shouldGenerateMatrix() {
        int n = 5;
        List<List<Integer>> matrix = CombinatoricHelper.generateApSample(n);
        assertEquals(n, matrix.size());
        for(List<Integer> row : matrix) {
            assertEquals(n, row.size());
        }
    }
}