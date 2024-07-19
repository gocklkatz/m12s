package io.gocklkatz.m12s.vrp;

import java.util.List;

public class Solution {

    private List<Route> routes;
    private double objectiveFunctionValue = Double.MAX_VALUE;

    public Solution(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public double getObjectiveFunctionValue() {
        return objectiveFunctionValue;
    }

    public void setObjectiveFunctionValue(double objectiveFunctionValue) {
        this.objectiveFunctionValue = objectiveFunctionValue;
    }
}
