package me.caiyuan.spring.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * YUAN
 * 7/21/16.
 */
public class CustomFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("CustomFilter ...");
        writer.flush();
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

}
