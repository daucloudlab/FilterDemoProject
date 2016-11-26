package kz.kaznitu.lessons.filter;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out = servletResponse.getWriter();
        servletResponse.setContentType("text/html; charset=utf-8");
        String pass = servletRequest.getParameter("pass");
        if(pass.equals("1234"))
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            out.println("You have enter a wrong password");
            RequestDispatcher rs = servletRequest.getRequestDispatcher("index.html");
            rs.include(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}
