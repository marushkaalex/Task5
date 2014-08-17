package com.epam.am.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private final static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        ActionFactory.actions.put("GET/login", new ShowLoginPageAction());
        ActionFactory.actions.put("POST/login", new ShowLoginPageAction());
        ActionFactory.actions.put("GETnull", new ShowPageAction("/index.jsp"));
        ActionFactory.actions.put("POST/logincheck", new LoginCheckAction());
        ActionFactory.actions.put("GET/home", new ShowPageAction("/home"));

    }

    public static Action getAction(String action) {
        return actions.get(action);
    }
}
