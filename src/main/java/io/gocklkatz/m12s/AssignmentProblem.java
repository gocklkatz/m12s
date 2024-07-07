package io.gocklkatz.m12s;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssignmentProblem {

    List<List<Integer>> matrix;

    public AssignmentProblem(List<List<Integer>> matrix) {
        this.matrix = matrix;
    }

    public List<Integer> solveAP() {
        List<Integer> sequence = Arrays.asList(0, 1, 2, 3);
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
        for(int i=0; i<4; i++) {
            total += matrix.get(i).get(solution.get(i));
        }
        return total;
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(List.of(9, 2, 7, 8));
        matrix.add(List.of(6, 4, 3, 7));
        matrix.add(List.of(5, 8, 1, 8));
        matrix.add(List.of(7, 6, 9, 4));

        AssignmentProblem ap = new AssignmentProblem(matrix);
        List<Integer> bestSolution = ap.solveAP();
        System.out.println(bestSolution);
    }
}
