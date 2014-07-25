package com.jesper.InputHandlers.catalogue;

import com.jesper.InputHandlers.InputHandler;
import com.jesper.InputWrapper;

/**
 * Created by Jesper on 7/6/2014.
 */
public class QuitInputHandler implements InputHandler {

    private InputWrapper wrapper;

    public QuitInputHandler(InputWrapper wrapper) {
        this.wrapper = wrapper;
    }

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
        wrapper.exit();
        return null;
    }
}
