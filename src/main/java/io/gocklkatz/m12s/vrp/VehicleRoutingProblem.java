package io.gocklkatz.m12s.vrp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehicleRoutingProblem {

    private final List<List<Integer>> distanceMatrix;

    public VehicleRoutingProblem(List<List<Integer>> distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        checkMatrix();
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
