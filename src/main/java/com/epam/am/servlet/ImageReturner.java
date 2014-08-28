package com.epam.am.servlet;

import com.epam.am.util.PropertyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImageReturner extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PropertyManager manager = PropertyManager.getManager(PropertyManager.STORAGE);
        String env = System.getenv(manager.getProperty("environment.var"));
        resp.setContentType("image/jpeg");
        String pathInfo = req.getPathInfo();
        System.out.println(pathInfo);
    }
}
