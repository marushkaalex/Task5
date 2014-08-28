package com.epam.am.servlet;

import com.epam.am.util.PropertyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageReturner extends HttpServlet {

    private final static String ENV_VAR = "environment.var";
    private final static String PATH = "path";
    private static PropertyManager manager;
    private static String storage;

    static {
        manager = PropertyManager.getManager(PropertyManager.STORAGE);
        storage = System.getenv(manager.getProperty(ENV_VAR)) + manager.getProperty(PATH);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String fileName = storage + pathInfo;
        String mime = req.getServletContext().getMimeType(fileName);
        if (mime == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        resp.setContentType(mime);
        File file = new File(fileName);
        if (!file.exists()) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        resp.setContentLength(((int) file.length()));

        FileInputStream in = new FileInputStream(file);
        OutputStream out = resp.getOutputStream();

        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        in.close();
        System.out.println(pathInfo);
    }
}
