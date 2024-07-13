package io.gocklkatz.m12s.assignment;

import io.gocklkatz.m12s.utils.Combinatorics;

import java.util.ArrayList;
import java.util.List;

public class AssignmentProblem {

    private final List<List<Integer>> matrix;

    public AssignmentProblem(List<List<Integer>> matrix) {
        this.matrix = matrix;
    }

    public Result<Integer> solveApFullEnumeration() {
        for(List<Integer> row : matrix) {
            if (row.size() != matrix.size()) {
                throw new IllegalArgumentException("Matrix must be square!");
            }
        }

        List<Integer> sequence = new ArrayList<>();
        for(int i=0; i<matrix.size(); i++) {
            sequence.add(i);
        }
        List<List<Integer>> permutations = Combinatorics.permutations(sequence);

        Result<Integer> bestResult = null;
        int zfBest = Integer.MAX_VALUE;
        for(List<Integer> solution : permutations) {
            int zfVal = calcZf(solution);
            if(zfVal < zfBest) {
                zfBest = zfVal;
                bestResult = new Result<>(solution, zfBest);
            }
        }

        return bestResult;
    }

    public Result<Integer> solveApGreedyHeuristic() {
        for(List<Integer> row : matrix) {
            if (row.size() != matrix.size()) {
                throw new IllegalArgumentException("Matrix must be square!");
            }
        }

        List<Integer> solution = new ArrayList<>();
        for(List<Integer> row : matrix) {

            int bestI = Integer.MAX_VALUE;
            int currentZfVal = Integer.MAX_VALUE;

            for(int i=0; i<row.size(); i++) {
                if(!solution.contains(i)) {
                    if(row.get(i)<currentZfVal){
                        currentZfVal = row.get(i);
                        bestI = i;
                    }
                }
            }
            solution.add(bestI);
        }

        return new Result<>(solution, calcZf(solution));
    }

    private int calcZf(List<Integer> solution) {
        int total = 0;
        for(int i=0; i<matrix.size(); i++) {
            total += matrix.get(i).get(solution.get(i));
        }
        return total;
    }
}
