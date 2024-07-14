package io.gocklkatz.m12s.tsp;

import java.util.List;

public class Solver {

    private int[][] distanceMatrix;
    private String tour;
    private int cost = Integer.MAX_VALUE;

    public Solver(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public void solve() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < distanceMatrix.length; i++) {
            sb.append(i);
        }

        int n = sb.length();
        Permutation permutation = new Permutation();
        permutation.permute(sb.toString(), 0, n-1);
        List<String> tours = permutation.getTours();

        for(String tour : tours) {
            int tmpCost = 0;
            int prev = 0;
            for (int i = 0; i < tour.length(); i++) {
                int next = Character.getNumericValue(tour.charAt(i));
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

    public String getTour() {
        return tour;
    }

    public int getCost() {
        return cost;
    }
}
