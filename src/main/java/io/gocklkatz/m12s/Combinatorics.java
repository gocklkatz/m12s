package io.gocklkatz.m12s;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.swap;

// https://www.baeldung.com/java-combinatorial-algorithms
// https://github.com/eugenp/tutorials/blob/afdc50e4d59306574a48a3470abb3b262d6abba0/algorithms-modules/algorithms-miscellaneous-5/src/main/java/com/baeldung/algorithms/combinatorics/Combinatorics.java
public class Combinatorics {

    public static List<List<Integer>> permutations(List<Integer> sequence) {
        List<List<Integer>> results = new ArrayList<>();
        permutationsInternal(sequence, results, 0);
        return results;
    }

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
