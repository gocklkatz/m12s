package io.gocklkatz.m12s.tsp;

public class Tsp {
    private int id;
    private int[][] distanceMatrix;
    private int cost;
    private String tour;

    public Tsp(int id, int[][] distanceMatrix) {
        this.id = id;
        this.distanceMatrix = distanceMatrix;
    }

    public void solve(){
        Solver solver = new Solver(distanceMatrix);
        solver.solve();
        tour = solver.getTour();
        cost = solver.getCost();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "Tsp{" +
                "id=" + id +
                ", distances=" + distanceMatrix +
                ", cost=" + cost +
                ", tour='" + tour + '\'' +
                '}';
    }
}
