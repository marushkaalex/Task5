package com.epam.am;

import com.epam.am.action.LoginCheckAction;

//TODO servlet
//TODO database
//TODO actions
//TODO properties
public class Runner {
    public static void main(String[] args) {
        LoginCheckAction action = new LoginCheckAction();
        action.hash("12345");
    }
}
