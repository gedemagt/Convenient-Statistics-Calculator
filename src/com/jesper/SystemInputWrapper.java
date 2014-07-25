package com.jesper;

import java.util.Scanner;

/**
 * Created by Jesper on 7/25/2014.
 */
public class SystemInputWrapper extends AbstractInputWrapper {

    private Scanner sc;

    @Override
    public void write(String s) {
        System.out.println(s);
    }

    @Override
    public void init() {
        sc = new Scanner(System.in);
        listenForInputs();
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    private void listenForInputs() {
        String s = sc.nextLine();
        processInput(s);
    }

    private void processInput(String s) {
        fireListeners(s);
        listenForInputs();
    }
}
