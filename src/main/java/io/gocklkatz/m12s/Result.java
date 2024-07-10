package io.gocklkatz.m12s;

import java.util.List;
import java.util.Objects;

public class Result<T> {

    private final List<T> solutionVector;
    private final T targetFunctionValue;

    public Result(List<T> solutionVector, T targetFunctionValue) {
        this.solutionVector = solutionVector;
        this.targetFunctionValue = targetFunctionValue;
    }

    public List<T> getSolutionVector() {
        return solutionVector;
    }

    public T getTargetFunctionValue() {
        return targetFunctionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result<?> result = (Result<?>) o;
        return Objects.equals(solutionVector, result.solutionVector) && Objects.equals(targetFunctionValue, result.targetFunctionValue);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(solutionVector);
        result = 31 * result + Objects.hashCode(targetFunctionValue);
        return result;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "solutionVector=" + solutionVector +
                ", targetFunctionValue=" + targetFunctionValue +
                '}';
    }
}
