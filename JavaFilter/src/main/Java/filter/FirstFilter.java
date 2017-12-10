package filter;

import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Thpffcj on 2017/12/7.
 */
public class FirstFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter.FirstFilter---init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter.FirstFilter---start");
//        chain.doFilter(request, response);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 重定向
//        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/main.jsp");

        // 转发
        httpServletRequest.getRequestDispatcher("/main.jsp").forward(request, response);

        System.out.println("filter.FirstFilter---end");
    }

    public void destroy() {
        System.out.println("filter.FirstFilter---destroy");
    }
}
