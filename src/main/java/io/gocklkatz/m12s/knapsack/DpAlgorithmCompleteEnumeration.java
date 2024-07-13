package io.gocklkatz.m12s.knapsack;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DpAlgorithmCompleteEnumeration implements DpAlgorithm {
    @Override
    public Result solve(List<Food> foods, int maxCost, Comparator<Food> comp) {
        double bestBenefit = 0;
        double bestCost = 0;
        double[] bestDecisionVariables = null;

        // Fill benefitCost matrix
        double[][] benefitCost = new double[foods.size()][2];
        for(int i=0; i<foods.size(); i++) {
            benefitCost[i][0] = foods.get(i).benefit();
            benefitCost[i][1] = foods.get(i).cost();
        }

        // For each decision variable vector
        double[] decisionVariables = new double[foods.size()];
        for (int i=0; i< Math.pow(2, foods.size()+1); i++){

            // Fill decisionVariables vector
            int mask = (int) Math.pow(2, foods.size()-1);
            int j = 0;
            while (mask > 0){
                if ((mask & i) == 0){
                    decisionVariables[j] = 0;
                } else {
                    decisionVariables[j] = 1;
                }
                mask = mask >> 1;
                j++;
            }

            // Multiply decision variable vector with benefitCost matrix
            RealMatrix matrix1 = new Array2DRowRealMatrix(decisionVariables).transpose();
            RealMatrix matrix2 = new Array2DRowRealMatrix(benefitCost);
            RealMatrix resMatrix = matrix1.multiply(matrix2);

            // Check for and remember currently best solution
            double currentBenefit = resMatrix.getEntry(0,0);
            double currentCost = resMatrix.getEntry(0,1);
            if(currentCost <= maxCost && currentBenefit > bestBenefit) {
                bestBenefit = currentBenefit;
                bestCost = currentCost;
                bestDecisionVariables = decisionVariables.clone();
            }
        }

        List<Food> resultList = new ArrayList<>();
        for(int i=0; i<bestDecisionVariables.length; i++) {
            if((int) bestDecisionVariables[i] == 1) {
                resultList.add(foods.get(i));
            }
        }
        //resultList.forEach(System.out::println);

        Result result = new Result();
        result.setTotalBenefit((int) bestBenefit);
        result.setTotalCost((int) bestCost);
        result.setFoods(resultList);

        return result;
    }
}
