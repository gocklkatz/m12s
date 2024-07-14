package io.gocklkatz.m12s.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.swap;

// https://www.baeldung.com/java-combinatorial-algorithms
// https://github.com/eugenp/tutorials/blob/afdc50e4d59306574a48a3470abb3b262d6abba0/algorithms-modules/algorithms-miscellaneous-5/src/main/java/com/baeldung/algorithms/combinatorics/Combinatorics.java
public class CombinatoricHelper {

    public static List<List<Integer>> permutationsFromToexcl(int from, int toExcl) {
        List<Integer> sequence = new ArrayList<>();
        for(int i=from; i<toExcl; i++) {
            sequence.add(i);
        }
        return CombinatoricHelper.permutations(sequence);
    }

    public static List<List<Integer>> permutations(List<Integer> sequence) {
        List<List<Integer>> results = new ArrayList<>();
        permutationsInternal(sequence, results, 0);
        return results;
    }

    // Also see https://en.wikipedia.org/wiki/Heap%27s_algorithm
    private static void permutationsInternal(List<Integer> sequence, List<List<Integer>> results, int index) {
        if (index == sequence.size() - 1) {
            results.add(new ArrayList<>(sequence));
            return;
        }

        for (int i = index; i < sequence.size(); i++) {
            swap(sequence, i, index);
            permutationsInternal(sequence, results, index + 1);
            swap(sequence, i, index);
        }
    }

    public static List<List<Integer>> generateApSample(int n) {
        Random rand = new Random();

        List<List<Integer>> matrix = new ArrayList<>();
        for(int i=0; i<n; i++){
            List<Integer> row = new ArrayList<>();
            for(int j=0; j<n; j++){
                row.add(rand.nextInt(10));
            }
            matrix.add(row);
        }
        return matrix;
    }
}
