package com.epam.am.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class EncodingFilter implements Filter {

    private static java.util.logging.Logger log = Logger.getLogger("");

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
