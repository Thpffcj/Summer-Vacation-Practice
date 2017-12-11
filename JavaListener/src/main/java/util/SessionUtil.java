package util;

import entity.User1;

import java.util.ArrayList;

/**
 * Created by Thpffcj on 2017/12/11.
 */
public class SessionUtil {

    public static Object getUserBySessionId(ArrayList<User1> userList, String sessionIdString) {
        for (int i = 0; i < userList.size(); i++) {
            User1 user = userList.get(i);
            if (user.getSessionIdString().equals(sessionIdString)) {
                return user;
            }
        }
        return null;
    }
}
