package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Thpffcj on 2017/12/10.
 */
@WebFilter(filterName = "AsynFilter", servletNames = "AsynServlet", asyncSupported=true, dispatcherTypes={DispatcherType.REQUEST,DispatcherType.ASYNC})
public class AsynFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("start.....AsynFilter");
        chain.doFilter(req, resp);
        System.out.println("end.....AsynFilter");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
