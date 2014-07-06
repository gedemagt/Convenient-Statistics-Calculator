package com.jesper;

import com.jesper.InputHandlers.*;
import com.jesper.InputHandlers.catalogue.*;

import java.util.Scanner;

public class Main {

    private static Scanner sc;
    private static InputHandlerManager inputHandler = new InputHandlerManager();

    public static void main(String[] args) {
        inputHandler.addInputHandler(new ErrorPropagationInputHandler());
        inputHandler.addInputHandler(new WeightedAverageInputHandler());
        inputHandler.addInputHandler(new AverageInputHandler());
        inputHandler.addInputHandler(new HelpInputHandler(inputHandler.getHandlerMap()));
        inputHandler.addInputHandler(new QuitInputHandler());
        System.out.println("Welcome to Convenient Statistics calculator");
        System.out.println("Type 'help' to see supported commands.");
        System.out.println("Type 'quit' to quit the program.");
        System.out.println("---------------------------------------------------");
        System.out.println("");
        sc = new Scanner(System.in);
        listenForInputs();
    }

    private static void listenForInputs() {
        String s = sc.nextLine();
        processInput(s);
    }

    private static void processInput(String s) {
            try {
                System.out.println(inputHandler.process(s));
                System.out.println("");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        listenForInputs();
    }
}
