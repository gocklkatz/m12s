package io.gocklkatz.m12s.vrp;

import java.util.List;

public class Solution {

    private List<Integer> route1;
    private List<Integer> route2;
    private double objectiveFunctionValue;

    public Solution(List<Integer> route1, List<Integer> route2) {
        this.route1 = route1;
        this.route2 = route2;
    }

    public List<Integer> getRoute1() {
        return route1;
    }

    public void setRoute1(List<Integer> vehicle1) {
        this.route1 = vehicle1;
    }

    public List<Integer> getRoute2() {
        return route2;
    }

    public void setRoute2(List<Integer> route2) {
        this.route2 = route2;
    }

    public double getObjectiveFunctionValue() {
        return objectiveFunctionValue;
    }

    public void setObjectiveFunctionValue(double objectiveFunctionValue) {
        this.objectiveFunctionValue = objectiveFunctionValue;
    }
}
