package io.gocklkatz.m12s.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.swap;

// https://www.baeldung.com/java-combinatorial-algorithms
// https://github.com/eugenp/tutorials/blob/afdc50e4d59306574a48a3470abb3b262d6abba0/algorithms-modules/algorithms-miscellaneous-5/src/main/java/com/baeldung/algorithms/combinatorics/Combinatorics.java
public class CombinatoricHelper {

    /*
     * Permutations
     * - A permutation is an act of rearranging a sequence in such a way that it has a different order.
     * - For a sequence of n elements, there are n! different permutations.
     */
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

    /*
     * Power set
     * - The power set of set S is the set of all subsets of S including the empty set and S itself.
     * - The power set contains 2^n subsets.
     */

    public static List<List<Character>> generatePowerSet(List<Character> sequence) {
        List<List<Character>> powerSet = new ArrayList<>();
        powerSetInternal(sequence, powerSet, new ArrayList<>(), 0);
        return powerSet;
    }

    private static void powerSetInternal(List<Character> set, List<List<Character>> powerSet,
                                         List<Character> accumulator, int index) {
        if (index == set.size()) {
            powerSet.add(new ArrayList<>(accumulator));
        } else {
            accumulator.add(set.get(index));
            powerSetInternal(set, powerSet, accumulator, index + 1);
            accumulator.removeLast();
            powerSetInternal(set, powerSet, accumulator, index + 1);
        }
    }

    /*
     * Generate all different combinations of distributing n items into m buckets
     */
    public static List<List<Integer>> generateCombinations(int n, int m) {
        List<List<Integer>> combinations = new ArrayList<>();
        int[] indices = new int[n];

        while (true) {
            // Add current combination to the list
            List<Integer> combination = new ArrayList<>();
            for (int index : indices) {
                combination.add(index);
            }
            combinations.add(combination);

            // Find the rightmost index that can be incremented
            int i;
            for (i = n - 1; i >= 0; i--) {
                if (indices[i] < m - 1) {
                    indices[i]++;
                    break;
                } else {
                    indices[i] = 0;
                }
            }

            // If no index could be incremented, we are done
            if (i < 0) {
                break;
            }
        }

        return combinations;
    }

    /*
     * Generate n by n matrix with random values 0 <= x < 10
     */
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
