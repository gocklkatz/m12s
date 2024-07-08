package io.gocklkatz.m12s;

import java.util.ArrayList;
import java.util.List;

public class AssignmentProblem {

    private final List<List<Integer>> matrix;

    public AssignmentProblem(List<List<Integer>> matrix) {
        this.matrix = matrix;
    }

    public List<Integer> solveAP() {
        List<Integer> firstRow = matrix.getFirst();
        if(matrix.size() != firstRow.size()) {
            throw new IllegalArgumentException("Matrix must be square!");
        }

        List<Integer> sequence = new ArrayList<>();
        for(int i=0; i<matrix.size(); i++) {
            sequence.add(i);
        }
        List<List<Integer>> permutations = Combinatorics.permutations(sequence);

        List<Integer> bestSolution = new ArrayList<>();
        int zfBest = Integer.MAX_VALUE;
        for(List<Integer> solution : permutations) {
            int zfVal = calcZf(solution);
            if(zfVal < zfBest) {
                zfBest = zfVal;
                bestSolution = solution;
            }
        }

        return bestSolution;
    }

    private int calcZf(List<Integer> solution) {
        int total = 0;
        for(int i=0; i<matrix.size(); i++) {
            total += matrix.get(i).get(solution.get(i));
        }
        return total;
    }
}
