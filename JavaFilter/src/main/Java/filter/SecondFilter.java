package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Thpffcj on 2017/12/7.
 */
public class SecondFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter.SecondFilter---init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter.SecondFilter---start");
        chain.doFilter(request, response);
        System.out.println("filter.SecondFilter---end");
    }

    public void destroy() {
        System.out.println("filter.SecondFilter---destroy");
    }
}
