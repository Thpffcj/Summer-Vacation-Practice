package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Thpffcj on 2017/12/10.
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "noLoginPaths", value = "login.jsp;fail.jsp;LoginServlet"),
        @WebInitParam(name = "charset", value = "UTF-8")})
public class LoginFilter implements Filter {

    private FilterConfig config;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        String noLoginPaths = config.getInitParameter("noLoginPaths");

        String charset = config.getInitParameter("charset");
        if (charset == null) {
            charset = "UTF-8";
        }
        request.setCharacterEncoding(charset);

        if (noLoginPaths != null) {
            String[] strArray = noLoginPaths.split(";");
            for (int i = 0; i < strArray.length; i++) {

                if (strArray[i] == null || "".equals(strArray[i])) {
                    continue;
                }

                if (httpServletRequest.getRequestURI().indexOf(strArray[i]) != -1){
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        if(session.getAttribute("username")!=null){
            chain.doFilter(request, response);
        }else{
            httpServletResponse.sendRedirect("login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
