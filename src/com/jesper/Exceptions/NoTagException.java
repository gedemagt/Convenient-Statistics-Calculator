package com.jesper.Exceptions;

/**
 * Created by Jesper on 7/6/2014.
 */
public class NoTagException extends Exception {

    public NoTagException(String tag) {
        super("Unknown tag: " + tag);
    }
}
