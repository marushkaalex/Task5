package com.epam.am.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    public static final String DATABASE = "database.properties";
    private static Properties PROPERTIES = new Properties();
    private static PropertyManager manager = null;
    private static String file;

    private PropertyManager(String fileName) {
        InputStream is = PropertyManager.class.getClassLoader().getResourceAsStream(fileName);
        file = fileName;
        try {
            PROPERTIES.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyManager getManager(String fileName) {
        if (manager == null || !file.equals(fileName)) {
            manager = new PropertyManager(fileName);
        }
        return manager;
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
