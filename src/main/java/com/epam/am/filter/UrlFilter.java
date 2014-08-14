package com.epam.am.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UrlFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req.get;
        if (request.getPathInfo() == null || request.getPathInfo().equals("/static")) {
            req.getRequestDispatcher("/do").forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
//        req.getRequestDispatcher(((HttpServletRequest) req).getPathInfo()).forward(req,resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
