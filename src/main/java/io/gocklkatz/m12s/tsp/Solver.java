package io.gocklkatz.m12s.tsp;

import io.gocklkatz.m12s.utils.CombinatoricHelper;

import java.util.List;

public class Solver {

    private final int[][] distanceMatrix;
    private List<Integer> tour;
    private int cost = Integer.MAX_VALUE;

    public Solver(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public void solve() {
        List<List<Integer>> permutations = CombinatoricHelper.
                permutationsFromToexcl(1, distanceMatrix.length);

        for(List<Integer> tour : permutations) {
            int tmpCost = 0;
            int prev = 0;
            for (int next : tour) {
                tmpCost += distanceMatrix[prev][next];
                prev = next;
            }
            tmpCost += distanceMatrix[prev][0];

            if(tmpCost<cost) {
                cost = tmpCost;
                this.tour = tour;
            }
        }
    }

    public List<Integer> getTour() {
        return tour;
    }

    public int getCost() {
        return cost;
    }
}
