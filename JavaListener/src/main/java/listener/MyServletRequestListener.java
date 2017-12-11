package listener;

import entity.User1;
import util.SessionUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Thpffcj on 2017/12/10.
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    // 在线用户List
    private ArrayList<User1> userList;

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        userList  = (ArrayList<User1>)servletRequestEvent.getServletContext().getAttribute("userList");

        if (userList == null) {
            userList = new ArrayList<User1>();
        }

        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String sessionIdString = request.getSession().getId();

        if (SessionUtil.getUserBySessionId(userList, sessionIdString) == null) {
            User1 user = new User1();
            user.setSessionIdString(sessionIdString);
            user.setFirstTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            user.setIpString(request.getRemoteAddr());
            userList.add(user);
        }
        servletRequestEvent.getServletContext().setAttribute("userList", userList);
    }
}
