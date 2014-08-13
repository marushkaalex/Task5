package com.epam.am.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println(((HttpServletRequest) req).getRequestURL().toString());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}