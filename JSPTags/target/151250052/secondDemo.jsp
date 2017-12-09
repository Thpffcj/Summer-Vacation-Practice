<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- redirect 标签的用法 -->
<%--<c:redirect url = "firstDemo.jsp">--%>
    <%--<c:param name="username">Lily</c:param>--%>
    <%--<c:param name="password">123456</c:param>--%>
<%--</c:redirect>--%>

<!-- url 标签的用法 -->
<c:if test="${1<3}">
    <c:set var="partUrl">aa</c:set>
</c:if>
<c:url value="http://localhost:8080/${partUrl}" var="newUrl" scope="session"></c:url>
<a href="${newUrl}">新的Url</a>

</body>
</html>