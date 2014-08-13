package com.epam.am.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    public static final String LOGIN_CHECK = "login_check";

    private final static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        ActionFactory.actions.put(LOGIN_CHECK, new LoginCheckAction());
    }

    public static Action getAction(String action) {
        return actions.get(action);
    }
}
