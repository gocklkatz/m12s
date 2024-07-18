package io.gocklkatz.m12s.vrp;

import io.gocklkatz.m12s.utils.CombinatoricHelper;

import java.util.ArrayList;
import java.util.List;

public class TspSolver {

    private final List<List<Integer>> distanceMatrix;

    public TspSolver(List<List<Integer>> distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public List<Integer> solve(List<Integer> inputSequence) {

        List<Integer> bestTour = new ArrayList<>();
        double cost = Double.MAX_VALUE;

        List<List<Integer>> permutations = CombinatoricHelper.permutations(inputSequence);
        for(List<Integer> tour : permutations) {
            double tmpCost = evalRoute(tour);

            if(tmpCost<cost) {
                cost = tmpCost;
                bestTour = tour;
            }
        }

        return bestTour;
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

}
