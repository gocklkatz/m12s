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

    public Solution solveCompleteEnumerationNoCapacityConstraintGivenTruckNumber(int givenTruckNumber){

        Solution bestSolution = new Solution(new ArrayList<>());

        int n = distanceMatrix.size() - 1;; // Number of customers
        int m = givenTruckNumber; // Number of trucks

        List<List<Integer>> combinations = CombinatoricHelper.generateCombinations(n, m);

        for(List<Integer> combination : combinations){

            List<Route> routes = aufteilung2routes(combination);

            List<Route> tspRoutes = new ArrayList<>();
            TspSolver tspSolver = new TspSolver(distanceMatrix);
            for(Route route : routes) {
                tspRoutes.add(new Route(tspSolver.solve(route.stops())));
            }

            Solution solution = new Solution(tspRoutes);
            solution.setObjectiveFunctionValue(calcZf(tspRoutes));

            if (solution.getObjectiveFunctionValue() < bestSolution.getObjectiveFunctionValue() &&
                solution.getRoutes().size() >= givenTruckNumber) {
                bestSolution = solution;
            }
        }

        return bestSolution;
    }

    public Solution solveCompleteEnumerationNoCapacityConstraint(){

        Solution bestSolution = new Solution(new ArrayList<>());

        int numberOfCustomers = distanceMatrix.size() - 1;

        int numberOfElements = (int) Math.pow(2,numberOfCustomers);

        for(int i=1; i<numberOfElements-1; i++) {

            String paddedBinaryString1 = generatePaddedBinaryString(i, numberOfCustomers);
            List<Integer> stops1 = generateTour(paddedBinaryString1);

            String paddedBinaryString2 = generatePaddedBinaryString(numberOfElements - i - 1, numberOfCustomers);
            List<Integer> stops2 = generateTour(paddedBinaryString2);

            TspSolver tspSolver = new TspSolver(distanceMatrix);
            Route route1 = new Route(tspSolver.solve(stops1));
            Route route2 = new Route(tspSolver.solve(stops2));
            List<Route> routes = new ArrayList<>(List.of(route1, route2));

            Solution solution = new Solution(routes);
            solution.setObjectiveFunctionValue(calcZf(routes));

            if(solution.getObjectiveFunctionValue() < bestSolution.getObjectiveFunctionValue()) {
                bestSolution = solution;
            }
        }

        return bestSolution;
    }

    public Solution singleSolutionNoCapacityConstraint() {
        Route route1 = new Route(List.of(3, 4, 5));
        Route route2 = new Route(List.of(6, 1, 2));
        List<Route> routes = new ArrayList<>(List.of(route1, route2));

        Solution solution = new Solution(routes);
        solution.setObjectiveFunctionValue(calcZf(routes));

        return solution;
    }

    private String generatePaddedBinaryString(int i, int length) {
        String unpaddedBinaryString = Integer.toBinaryString(i);
        return String.
                format("%1$" + length + "s", unpaddedBinaryString).
                replace(' ', '0');
    }

    private List<Integer> generateTour(String assignment) {
        List<Integer> tour = new ArrayList<>();

        for(int i=assignment.length()-1; i>=0; i--) {
            int active = Integer.parseInt(assignment.substring(i,i+1));
            if(active == 1) {
                tour.add(assignment.length()-i);
            }
        }

        return tour;
    }

    private double calcZf(List<Route> routes) {
        double cost = 0;
        for(Route route : routes) {
            cost += evalRoute(route.stops());
        }
        return cost;
    }

    private double evalRoute(List<Integer> stops) {
        double cost = 0;

        int prev = 0;
        for (int next : stops) {
            cost += distanceMatrix.get(prev).get(next);
            prev = next;
        }
        cost += distanceMatrix.get(prev).getFirst();

        return cost;
    }

    private List<Route> aufteilung2routes(List<Integer> aufteilung) {

        List<Route> routes = new ArrayList<>();

        List<List<Integer>> collector = new ArrayList<>();
        for(Integer xxx : aufteilung) {
            collector.add(new ArrayList<>());
        }

        for(int i=0; i<collector.size(); i++){
            collector.get(aufteilung.get(i)).add(i+1);
        }

        for (List<Integer> integers : collector) {
            if (!integers.isEmpty()) {
                routes.add(new Route(integers));
            }
        }

        return routes;
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
