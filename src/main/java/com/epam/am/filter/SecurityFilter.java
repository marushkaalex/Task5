package com.epam.am.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    public static final String ADMIN_HEADER = "<dib style=\"position: fixed; padding: 10px; background: aliceblue; text-align: center; width: 100%; top: 0; left: 0px\">If you see it you probably an admin</dib>";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter(((HttpServletRequest) req), ((HttpServletResponse) resp), chain);
    }

    private void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        User user = (User) req.getSession().getAttribute("user");
//        if (user != null) {
//            resp.getWriter().write(ADMIN_HEADER);
//        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
