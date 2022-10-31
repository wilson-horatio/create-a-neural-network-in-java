package com.horatio;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {

    private static final String NUMBER_FORMAT = "%+12.5f";
    private static final double TOLERANCE = 0.000001;
    private int rows;
    private int cols;

    public interface Producer {
        double produce(int index);
    }

    public interface ValueProducer {
        double produce(int index, double value);
    }

    private double[] a;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        a = new double[rows * cols];
    }

    public Matrix(int rows, int cols, Producer producer) {
        this(rows, cols);

        for(int i = 0; i < a.length; i++) {
            a[i] = producer.produce(i);
        }
    }

    public Matrix apply(ValueProducer producer) {
        Matrix result = new Matrix(rows, cols);

        for(int i = 0; i < a.length; i++) {
            result.a[i] = producer.produce(i, a[i]);
        }

        return result;
    }

    public double get(int index) {
        return a[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                sb.append(String.format(NUMBER_FORMAT, a[index]));
                index++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matrix matrix = (Matrix) o;
        for(int i = 0; i < a.length; i++) {
            if(Math.abs(a[i] - matrix.a[i]) > TOLERANCE) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.hashCode(a);
        return result;
    }
}
