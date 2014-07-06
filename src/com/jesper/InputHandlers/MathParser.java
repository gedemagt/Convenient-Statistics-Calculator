package com.jesper.InputHandlers;

/**
 * Created by Jesper on 7/6/2014.
 */
public class MathParser {

    public static String parse(String s){
        s = s.replace("pi", Math.PI + "");
        return s;
    }
}
