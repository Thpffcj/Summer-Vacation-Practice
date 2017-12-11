<%
    request.removeAttribute("requestName");
    request.getSession().removeAttribute("sessionName");
    request.getSession().getServletContext().removeAttribute("contextName");

    request.getSession().removeAttribute("currentUser");
%>
<html>
<body>
<h2>这是销毁页面!</h2>
</body>
</html>
