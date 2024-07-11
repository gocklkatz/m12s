package io.gocklkatz.m12s;

import java.util.List;
import java.util.Objects;

/*
    Objective function (Zielfunktion)

    The function f is called, variously, an objective function, criterion function a loss function or
    cost function (minimization),[6] a utility function or fitness function (maximization), or, in
    certain fields, an energy function or energy functional.

    A feasible solution that minimizes (or maximizes, if that is the goal) the objective function is
    called an optimal solution.

    ---
    Q/A
    - Why do I need heuristics/meta-heurstics? -> x
    - Is assignment problem (AP) an x?
    - Solution methods for the AP. (Hungarian)
      # Brute force. n! permutations
      # Greedy algorithm (heuristic)
      # Hungarian method (?)
 */
public class Result<T> {

    private final List<T> solutionVector;
    private final T objectiveFunctionValue;

    public Result(List<T> solutionVector, T objectiveFunctionValue) {
        this.solutionVector = solutionVector;
        this.objectiveFunctionValue = objectiveFunctionValue;
    }

    public List<T> getSolutionVector() {
        return solutionVector;
    }

    public T getTargetFunctionValue() {
        return objectiveFunctionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result<?> result = (Result<?>) o;
        return Objects.equals(solutionVector, result.solutionVector) && Objects.equals(objectiveFunctionValue, result.objectiveFunctionValue);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(solutionVector);
        result = 31 * result + Objects.hashCode(objectiveFunctionValue);
        return result;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "solutionVector=" + solutionVector +
                ", targetFunctionValue=" + objectiveFunctionValue +
                '}';
    }
}
