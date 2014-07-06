package com.jesper.InputHandlers.catalogue;

import com.jesper.Exceptions.NoTagException;
import com.jesper.InputHandlers.InputHandler;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Jesper on 7/6/2014.
 */
public class HelpInputHandler implements InputHandler {

    private Map<String, InputHandler> handlers;

    public HelpInputHandler(Map<String, InputHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public String getTag() {
        return "help";
    }

    @Override
    public String process(String s) throws Exception {
        if(s.equals("")) {
            StringBuilder b = new StringBuilder();
            b.append("Type 'help <command>' to get detailed help.\n\n");
            b.append("Available commands:\n");
            for(InputHandler h : handlers.values()) {
                if(h.getTag().equals(getTag())) continue;
                b.append(h.getTag()).append("\n");
            }
            // Remove last \n
            b.delete(b.length()-1, b.length());
            return b.toString();
        }
        InputHandler h = handlers.get(s);
        if(h == null) throw new NoTagException(s);
        return h.getHelp();
    }
}
