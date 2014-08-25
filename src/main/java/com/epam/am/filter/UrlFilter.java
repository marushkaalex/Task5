package com.epam.am.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UrlFilter implements Filter {

    private static java.util.logging.Logger log = java.util.logging.Logger.getLogger("");

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter(((HttpServletRequest) req), ((HttpServletResponse) resp), chain);
    }

    private void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String pathInfo = req.getRequestURI().substring(req.getContextPath().length());
        System.out.println(pathInfo);
        if (pathInfo.startsWith("/static") || pathInfo.startsWith("/webjars")) {
            chain.doFilter(req, resp);
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/do" + pathInfo);
        dispatcher.forward(req, resp);
//        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
