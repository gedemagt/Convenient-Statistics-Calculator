package com.jesper.InputHandlers;

/**
 * Created by Jesper on 7/4/2014.
 */
public interface InputHandler {

    public String getHelp();
    public String getTag();
    public String process(String s) throws Exception;
}
