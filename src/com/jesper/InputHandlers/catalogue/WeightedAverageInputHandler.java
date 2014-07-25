package com.jesper.InputHandlers.catalogue;

import com.jesper.InputHandlers.InputHandler;

/**
 * Created by Jesper on 7/4/2014.
 */
public class WeightedAverageInputHandler implements InputHandler {

    private double[] means;
    private double[] devs;

    private double mean = 0;
    private double dev = 0;

    @Override
    public String getTag() {
        return "wavg";
    }

    @Override
    public String process(String input) throws Exception {
        // Remove whitespaces
        input = input.replace(" ", "");
        input = input.replace("\t", "");

        // Split the string
        String[] args = input.split(",");

        if((args.length)%2 != 0) throw new Exception("Invalid number of parameters!");

        // Save the expression
        String exp = args[0];

        // Extract the variables
        means = new double[args.length/2];
        devs = new double[args.length/2];
        for(int i=0; i<means.length; i++) {
            means[i] = Double.parseDouble(args[i*2+0]);
            devs[i] = Double.parseDouble(args[i*2+1]);
        }

        // Create expression
        double mean_sum = 0.0;
        double w_sum = 0.0;
        for(int i=0; i<means.length; i++) {
            double x = means[i];
            double s = devs[i];
            mean_sum += x/(s*s);
            w_sum += 1/(s*s);
        }
        mean = mean_sum/w_sum;
        dev = 1/Math.sqrt(w_sum);
        return rapport();
    }

    private String rapport() {
        StringBuilder b = new StringBuilder();
        b.append("Mean: ").append(mean).append("\n");
        b.append("Dev: ").append(dev);
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
