<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.User1" %>
<%
    request.setAttribute("requestName", "requestValue");
    request.getSession().setAttribute("sessionName", "sessionValue");
    request.getSession().getServletContext().setAttribute("contextName", "contextValue");

    request.getSession().setAttribute("currentUser", new entity.User());
%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h2>Hello World!</h2>
<button onclick="location.href='<%=request.getContextPath() %>/destory.jsp'">Destory</button><br>
当前在线用户总数:${userNumber}
<%
    ArrayList<User1> userList = (ArrayList<User1>) request.getServletContext().getAttribute("userList");
    if (userList != null) {
        for (int i = 0; i < userList.size(); i++) {
            User1 user1 = userList.get(i);
%>
    IP:<%=user1.getIpString()%>, FirstTime:<%=user1.getFirstTimeString()%>, SessionId:<%=user1.getSessionIdString()%><br>
<%
    }}
%>
</body>
</html>
