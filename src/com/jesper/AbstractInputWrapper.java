package com.jesper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 7/25/2014.
 */
public abstract class AbstractInputWrapper implements InputWrapper {

    private List<InputListener> listeners = new ArrayList<InputListener>();

    @Override
    public void listen(InputListener listener) {
        listeners.add(listener);
    }

    protected void fireListeners(String s) {
        for (InputListener l : listeners) {
            l.onInput(s);
        }
    }
}
