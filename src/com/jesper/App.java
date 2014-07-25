package com.jesper;

import com.jesper.InputHandlers.InputHandlerManager;
import com.jesper.InputHandlers.catalogue.*;

/**
 * Created by Jesper on 7/25/2014.
 */
public class App {

    private InputWrapper wrapper;
    private InputHandlerManager inputHandler;

    public App(InputWrapper wrapper) {
        this.wrapper = wrapper;
        wrapper.listen(new InputListener() {
            @Override
            public void onInput(String input) {
                processInput(input);
            }
        });
        setupInputHandler();
        printWelcome();
        wrapper.init();
    }

    private void printWelcome() {
        wrapper.write("Welcome to Convenient Statistics calculator");
        wrapper.write("Type 'help' to see supported commands.");
        wrapper.write("Type 'quit' to quit the program.");
        wrapper.write("---------------------------------------------------");
        wrapper.write("");
    }

    private void setupInputHandler() {
        inputHandler = new InputHandlerManager();
        inputHandler.addInputHandler(new ErrorPropagationInputHandler());
        inputHandler.addInputHandler(new WeightedAverageInputHandler());
        inputHandler.addInputHandler(new AverageInputHandler());
        inputHandler.addInputHandler(new HelpInputHandler(inputHandler.getHandlerMap()));
        inputHandler.addInputHandler(new QuitInputHandler(wrapper));
    }

    private void processInput(String input) {
        try {
            wrapper.write(inputHandler.process(input));
            wrapper.write("");
        } catch (Exception e) {
            wrapper.write("Error: " + e.getMessage());
        }
    }


}
