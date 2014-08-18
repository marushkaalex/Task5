package com.epam.am.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private final static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        ActionFactory.actions.put("GET/login", new ShowLoginPageAction());
        ActionFactory.actions.put("POST/login", new ShowLoginPageAction());
        ActionFactory.actions.put("POST/logincheck", new LoginCheckAction());
        ActionFactory.actions.put("GET/home", new ShowPageAction("home"));
        ActionFactory.actions.put("GET/index", new ShowPageAction("index"));
        ActionFactory.actions.put("GET/", new ShowPageAction("index"));
        ActionFactory.actions.put("GET/logout", new LogoutAction());
        ActionFactory.actions.put("GET/register", new ShowPageAction("register"));
        ActionFactory.actions.put("POST/checkRegister", new RegisterCheckAction());

    }

    public static Action getAction(String action) {
        return actions.get(action);
    }
}
