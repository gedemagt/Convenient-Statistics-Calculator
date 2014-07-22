package com.jesper.InputHandlers;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jesper on 7/5/2014.
 */
public class InputHandlerManager {

    private Map<String, InputHandler> inputs = new TreeMap<String, InputHandler>();
    private InputHandler defaultHandler = null;

    public InputHandlerManager() {
        addInputHandler(new DefaultHandler());
    }

    public void addInputHandler(InputHandler handler) {
        inputs.put(handler.getTag(), handler);
    }

    public Map<String, InputHandler> getHandlerMap() {return inputs;}

    public String process(String s) throws Exception {

        // To lower case for further parsing
        s = s.toLowerCase();

        // Parse math
        s = MathParser.parse(s);

        int first_space = s.indexOf(" ");
        
        if(first_space == -1) {
            if(inputs.containsKey(s)) return inputs.get(s).process("");
            if(defaultHandler == null) throw new Exception("No default handler specified");
            else return defaultHandler.process(s);
        }

        String tag = s.substring(0, first_space);
        String exp = s.substring(first_space+1);

        if(inputs.containsKey(tag)) {
            return inputs.get(tag).process(exp);
        }
        throw new Exception("Unknown tag: " + tag);
    }

    private class DefaultHandler implements InputHandler {

        public String getHelp(){
            return "Default help!";
        }
        public String getTag(){
            return "default";
        }
        public String process(String s) throws Exception {
            InputHandler h = inputs.get(s);
            if (h == null) throw new Exception("Unknown tag: " + s);
            else defaultHandler = h;
            return "New default input: " + s;
        }

    }
}
