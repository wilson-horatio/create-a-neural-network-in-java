package com.horatio;

public class App {
    public static void main(String[] args) {

        // Inputs
        double[] x = {0, 1};
        // Weights
        double[] w = {0.5, 0.5};
        // Bias
        double b = 0.5;
        // Weighted sum
        double z = 0.0;

        for(int i = 0; i < x.length; i++) {
            z += x[i] * w[i];
        }

        z += b;

        // Activation function
        double a = z > 0 ? 1.0: 0.0;

        // Print activation (output)
        System.out.println(a);
    }
}
