package com.epam.am.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private final static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        ActionFactory.actions.put("GET/login", new ShowLoginPageAction());
        ActionFactory.actions.put("POST/login", new LoginCheckAction());
        ActionFactory.actions.put("GET/home", req -> new ActionResult("", true));
        ActionFactory.actions.put("GET/index", new ShowPageAction("index"));
        ActionFactory.actions.put("GET/acc", new ShowPageIfLoggedInAction("acc", false, "login", true));
        ActionFactory.actions.put("POST/acc", new CommitUserOptionsAction());
        ActionFactory.actions.put("GET/", new ShowPageIfLoggedInAction("home", false, "index", false));
        ActionFactory.actions.put("GET/logout", new LogoutAction());
        ActionFactory.actions.put("GET/register", new ShowPageIfLoggedInAction("", true, "register", false));
        ActionFactory.actions.put("POST/register", new RegisterCheckAction());
        ActionFactory.actions.put("POST/upload", new ImageUploadAction());
        ActionFactory.actions.put("GET/upload", new ShowPageAction("upload"));
        ActionFactory.actions.put("GET/gallery", new ShowPageAction("gallery"));

    }

    public static Action getAction(String action) {
        return actions.get(action);
    }
}
