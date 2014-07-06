package com.jesper.InputHandlers.catalogue;

import com.jesper.InputHandlers.InputHandler;

/**
 * Created by Jesper on 7/4/2014.
 */
public class AverageInputHandler implements InputHandler {

    private double[] means;

    private double mean = 0;
    private double dev = 0;

    @Override
    public String getTag() {
        return "avg";
    }

    @Override
    public String process(String input) throws Exception {
        // Remove whitespaces
        input = input.replace(" ", "");
        input = input.replace("\t", "");

        // Split the string
        String[] args = input.split(",");

        // Extract the variables
        means = new double[args.length];
        for(int i=0; i<means.length; i++) {
            means[i] = Double.parseDouble(args[i]);
        }

        // Create expression
        mean = calcMean(means);
        dev = calcStd(means, mean);
        return rapport();
    }

    double calcMean(double[] values) {
        double sum = 0.0;
        for(double d : values) {
            sum += d;
        }
        return sum/(double) values.length;
    }

    double calcStd(double[] values, double mean) {
        double sum = 0.0;
        for(double d : values) {
            sum += Math.pow(d-mean, 2);
        }
        return Math.sqrt(sum/((double) values.length - 1.0));
    }

    private String rapport() {
        StringBuilder b = new StringBuilder();
        b.append("Mean: ").append(mean).append("\n");
        b.append("s(x): ").append(dev).append("\n");
        b.append("s(mean): ").append(dev/Math.sqrt((double) means.length));
        return b.toString();
    }

    @Override
    public String getHelp() {
        StringBuilder b = new StringBuilder();
        b.append("Syntax: \n");
        b.append("<mean1>,<dev1>,<mean2>,<dev2>,...\n");
        return b.toString();
    }

}
