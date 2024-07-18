package io.gocklkatz.m12s.vrp;

import io.gocklkatz.m12s.utils.CombinatoricHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehicleRoutingProblem {

    private final List<List<Integer>> distanceMatrix;

    public VehicleRoutingProblem(List<List<Integer>> distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        checkMatrix();
    }

    public Solution solveCompleteEnumeration(){

        Solution bestSolution = new Solution(new ArrayList<>(), new ArrayList<>());
        bestSolution.setObjectiveFunctionValue(Double.MAX_VALUE);

        int length = 6;
        int numberOfElements = (int) Math.pow(2,length);
        for(int i=1; i<numberOfElements-1; i++) {
            //TODO The "convert to binary" parts are awfully similar
            String unpaddedBinaryString1 = Integer.toBinaryString(i);
            String paddedBinaryString1 = String.
                    format("%1$" + length + "s", unpaddedBinaryString1).
                    replace(' ', '0');

            List<Integer> route1 = generateTour(paddedBinaryString1);

            int j = numberOfElements - i - 1;
            String unpaddedBinaryString2 = Integer.toBinaryString(j);
            String paddedBinaryString2 = String.
                    format("%1$" + length + "s", unpaddedBinaryString2).
                    replace(' ', '0');

            List<Integer> route2 = generateTour(paddedBinaryString2);

            //All permutation for route2 for each permutation of route1
            List<List<Integer>> permutations1 = CombinatoricHelper.permutations(route1);
            for(List<Integer> route1Perm : permutations1) {

                List<List<Integer>> permutations2 = CombinatoricHelper.permutations(route2);
                for(List<Integer> route2Perm : permutations2) {

                    Solution solution = new Solution(route1Perm, route2Perm);
                    solution.setObjectiveFunctionValue(calcZf(solution));

                    if(solution.getObjectiveFunctionValue() < bestSolution.getObjectiveFunctionValue()) {
                        bestSolution = solution;
                    }
                }
            }
        }

        return bestSolution;
    }

    private static List<Integer> generateTour(String assignment) {
        List<Integer> tour = new ArrayList<>();

        for(int i=assignment.length()-1; i>=0; i--) {
            int active = Integer.parseInt(assignment.substring(i,i+1));
            if(active == 1) {
                tour.add(assignment.length()-i);
            }
        }

        return tour;
    }

    public Solution singleSolution() {
        List<Integer> route1 = new ArrayList<>(List.of(3, 4, 5));
        List<Integer> route2 = new ArrayList<>(List.of(6, 1, 2));
        Solution solution = new Solution(route1, route2);

        solution.setObjectiveFunctionValue(calcZf(solution));

        return solution;
    }

    private double calcZf(Solution solution) {
        double cost = 0;
        cost += evalRoute(solution.getRoute1());
        cost += evalRoute(solution.getRoute2());
        return cost;
    }

    private double evalRoute(List<Integer> route) {
        double cost = 0;

        int prev = 0;
        for (int next : route) {
            cost += distanceMatrix.get(prev).get(next);
            prev = next;
        }
        cost += distanceMatrix.get(prev).getFirst();

        return cost;
    }

    private void checkMatrix() {
        for(List<Integer> line : distanceMatrix) {
            if(line.size() != distanceMatrix.size()) {
                throw new IllegalArgumentException("Distance matrix is not square!");            }
        }

        for(int i = 0; i< distanceMatrix.size(); i++) {
            for(int j = 0; j< distanceMatrix.size(); j++) {
                if(!Objects.equals(distanceMatrix.get(i).get(j), distanceMatrix.get(j).get(i))) {
                    throw new IllegalArgumentException("Distance matrix is not symmetric!");
                }
            }
        }
    }
}
