package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Thpffcj on 2017/12/10.
 */
public class ErrorFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("检测到有错误信息");
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
