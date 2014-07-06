package com.jesper.InputHandlers.catalogue;

import com.jesper.InputHandlers.InputHandler;
import de.congrace.exp4j.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jesper on 7/4/2014.
 */
public class ErrorPropagationInputHandler implements InputHandler {

    private String[] varNames;
    private double[] means;
    private double[] devs;

    private double mean = 0;
    private double dev = 0;

    @Override
    public String getTag() {
        return "errprop";
    }

    @Override
    public String process(String s) throws Exception {
        // Remove whitespaces
        s = s.replace(" ", "");
        s = s.replace("\t", "");
        // Split the string
        String[] args = s.split(",");

        if((args.length - 1)%3 != 0) throw new Exception("Invalid number of parameter input!");

        // Save the expression
        String exp = args[0];

        // Extract the variables
        varNames = new String[(args.length-1)/3];
        means = new double[(args.length-1)/3];
        devs = new double[(args.length-1)/3];
        for(int i=0; i<varNames.length; i++) {
            varNames[i] = args[i*3+1];
            means[i] = Double.parseDouble(args[i*3+2]);
            devs[i] = Double.parseDouble(args[i*3+3]);
        }

        // Create expression
        try {
            Calculable calc = new ExpressionBuilder(exp).withVariableNames(varNames).withCustomFunctions(getCustomFunctions()).build();
            mean = calc.calculate(means);
            dev = error(calc);
        } catch (UnknownFunctionException e) {
            throw new Exception("Unknown function!");
        } catch (UnparsableExpressionException e) {
            throw new Exception("Functions cannot be parsed!");
        }

        return rapport();
    }

    private String rapport() {
        StringBuilder b = new StringBuilder();
        b.append("Mean: ").append(mean).append("\n");
        b.append("Dev: ").append(dev);
        return b.toString();
    }

    private Collection<CustomFunction> getCustomFunctions() {
        ArrayList<CustomFunction> cf = new ArrayList<CustomFunction>();
        try {
            cf.add(new CustomFunction("log") {
                public double applyFunction(double... value) {
                    return Math.log(value[0]);
                }
            });
            cf.add(new CustomFunction("log10") {
                public double applyFunction(double... value) {
                    return Math.log10(value[0]);
                }
            });
        } catch (InvalidCustomFunctionException e) {
        }
        return cf;
    }

    private double error(Calculable calc) {
        double s = 0;
        for(int i=0; i<varNames.length; i++) {
            double d1 = calcDiv(calc, varNames[i], means[i]);
            s+=devs[i]*devs[i]*d1*d1;
        }
        return Math.sqrt(s);
    }

    private double calcDiv(Calculable calc, String var, double mean) {
        double epsilon = 1e-10;
        calc.setVariable(var, mean - epsilon);
        double left = calc.calculate();
        calc.setVariable(var, mean + epsilon);
        double right = calc.calculate();
        calc.setVariable(var, mean);
        return (right - left) / (2.0 * epsilon);
    }

    @Override
    public String getHelp() {
        StringBuilder b = new StringBuilder();
        b.append("Syntax: \n");
        b.append("<expression>,<var1>,<mean1>,<dev1>,<var2>,<mean2>,<dev2>,...\nExample: sin(x+y),x,1,2,y,1,2\n");
        b.append("Supported functions: \n");
        b.append("abs,acos,asin,atan,cbrt,ceil,cos,cosh,exp,floor,log,sin,sinh,sqrt,tan,tanh,log,log10");
        return b.toString();
    }

}
