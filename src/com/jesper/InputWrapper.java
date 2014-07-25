package com.jesper;

/**
 * Created by Jesper on 7/25/2014.
 */
public interface InputWrapper {

    public void write(String s);
    public void init();
    public void exit();
    public void listen(InputListener listener);
}
