package com.jesper.InputHandlers.catalogue;

import com.jesper.InputHandlers.InputHandler;

/**
 * Created by Jesper on 7/6/2014.
 */
public class QuitInputHandler implements InputHandler {
    @Override
    public String getHelp() {
        return "Quit the program!";
    }

    @Override
    public String getTag() {
        return "quit";
    }

    @Override
    public String process(String s) throws Exception {
        System.exit(0);
        return null;
    }
}
