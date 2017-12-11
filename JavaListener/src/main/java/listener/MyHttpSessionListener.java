package listener;

import entity.User1;
import util.SessionUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

/**
 * Created by Thpffcj on 2017/12/10.
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    private int userNumber = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        userNumber++;
        httpSessionEvent.getSession().getServletContext().setAttribute("userNumber", userNumber);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        userNumber--;
        httpSessionEvent.getSession().getServletContext().setAttribute("userNumber", userNumber);

        ArrayList<User1> userList = null;//在线用户List

        userList = (ArrayList<User1>)httpSessionEvent.getSession().getServletContext().getAttribute("userList");

        if(SessionUtil.getUserBySessionId(userList, httpSessionEvent.getSession().getId())!=null){
            userList.remove(SessionUtil.getUserBySessionId(userList, httpSessionEvent.getSession().getId()));
        }
    }
}
