<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<jsp:useBean id="person" class="test.Person"></jsp:useBean>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 使用out标签输出常量 -->
<c:out value="This is our first JSTL demo"></c:out><br>
<c:out value="${username}"></c:out>
<c:out value="${1+2}"></c:out>
<%String username = "mike"; request.setAttribute("username", username); %>

<c:out value="${empty username}"></c:out>

<!-- 使用out标签输出变量 -->
<!-- 直接输出变量 -->
<%session.setAttribute("name", "Jessica"); %>
<c:out value="${name}"></c:out>
<!-- 当变量不存在时，通过default属性输出默认值 -->
<c:out value="${name1}" default="error"></c:out><br>
<!-- 设置输出转义后的字符需要escapeXml属性值设置成false -->
<c:out value="&ltout标签&gt" escapeXml="false"></c:out><br>


<!-- 存值到scope中 -->
<c:set value="today" var="day" scope="session"></c:set>
<c:out value="${day}"></c:out><br>

<c:set var="age" scope="application">eleven</c:set>
<c:out value="${age}"></c:out><br>

<!-- 通过set标签向person中赋值 -->
<c:set target="${person}" property="name2" value="Mike"></c:set>
<c:out value="${person.name2}"></c:out>&nbsp;&nbsp;
<c:set target="${person}" property="age2" value="26"></c:set>
<c:out value="${person.age2}"></c:out>&nbsp;&nbsp;
<c:set target="${person}" property="address" value="北京市 朝阳区"></c:set>
<c:out value="${person.address}"></c:out>&nbsp;&nbsp;<br>

<c:set target="${person}" property="name2">Mike</c:set>
<c:out value="${person.name2}"></c:out>&nbsp;&nbsp;
<c:set target="${person}" property="age2">26</c:set>
<c:out value="${person.age2}"></c:out>&nbsp;&nbsp;
<c:set target="${person}" property="address">北京市 朝阳区</c:set>
<c:out value="${person.address}"></c:out>&nbsp;&nbsp;<br>

<!-- remove标签的用法 -->
<c:set var="lastName" value="Lily"></c:set>
<c:out value="${lastName}"></c:out>
<c:set var="firstName" value="Wang"></c:set>
<%--<c:remove var="firstName" />--%>
<c:out value="${firstName}"></c:out><br>

<!-- catch标签的用法 -->
<c:catch var="error">
    <c:set target="aa" property="bb">abc</c:set>
</c:catch>
<c:out value="${error}"></c:out><br>

<!-- if标签的用法 -->
<form action="firstDemo.jsp" method="post">
    <input type="text" name="score" value="${param.score}">
    <input type="submit" />
</form>
<!-- 优秀 score>=90 -->
<%--<c:if test="${param.score >= 90}" var="result" scope="application">--%>
    <%--<c:out value="恭喜，你的成绩是优秀"></c:out>--%>
<%--</c:if>--%>
<%--<c:out value="${applicationScope.result}"></c:out>--%>

<!-- choose when otherwise 标签的用法 -->
<c:choose>
    <c:when test="${param.score >= 90 && param.score <= 100}">
        <c:out value="优秀"></c:out>
    </c:when>
    <c:when test="${param.score >= 80 && param.score < 90}">
        <c:out value="良好"></c:out>
    </c:when>
    <c:when test="${param.score >= 70 && param.score < 80}">
        <c:out value="中等"></c:out>
    </c:when>
    <c:when test="${param.score >= 60 && param.score < 70}">
        <c:out value="及格"></c:out>
    </c:when>
    <c:when test="${param.score >= 0 && param.score < 60}">
        <c:out value="不及格"></c:out>
    </c:when>
    <c:otherwise>
        <c:out value="你的输入有问题"></c:out>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${param.score == 100}">
        <c:out value="太棒了，你是第一名"></c:out>
    </c:when>
</c:choose>

<%
    List<String> fruits = new ArrayList<String>();
    fruits.add("apple");
    fruits.add("orange");
    fruits.add("pear");
    fruits.add("watermelon");
    fruits.add("banana");
    fruits.add("grape");
    request.setAttribute("fruits", fruits);
%>
<!-- forEach 标签的用法 -->
<!-- 用法一：全部遍历 -->
<c:forEach var="fruit" items="${fruits}">
    <c:out value="${fruit}"></c:out><br>
</c:forEach>

<!-- 用法二：部分遍历 -->
<c:forEach var="fruit" items="${fruits}" begin="1" end="4">
    <c:out value="${fruit}"></c:out><br>
</c:forEach>

<!-- 用法三：部分遍历并且指定步长 -->
<c:forEach var="fruit" items="${fruits}" begin="1" end="4" step="2">
    <c:out value="${fruit}"></c:out><br>
</c:forEach>

<!-- 用法四：部分遍历并且指定步长并输出元素状态 -->
<c:forEach var="fruit" items="${fruits}" begin="1" end="4" varStatus="fru">
    <c:out value="${fruit}的四个属性值"></c:out><br>
    <c:out value="index属性值 ：${fru.index}"></c:out><br>
    <c:out value="count属性值：${fru.count}"></c:out><br>
    <c:out value="first属性值：${fru.first}"></c:out><br>
    <c:out value="last属性：${fru.last}"></c:out><br>
</c:forEach>

<!-- forTokens 标签的用法 -->
<c:forTokens items="010-88096789-123" delims="-" var="num">
    <c:out value="${num}"></c:out>
</c:forTokens>

<!-- import标签的用法 -->
<!-- 导入网络上的绝对路径 -->
<c:catch var="error09">
    <c:import url="https://www.imooc.com"></c:import>
</c:catch>
<c:out value="${error09}"></c:out><br>

<!-- 导入相对路径文件 -->
<c:catch var="error08">
    <c:import url="tt.txt" charEncoding="utf-8"></c:import>
</c:catch>
<c:out value="${error08}"></c:out><br>

<!-- var及scope属性的用法 -->
<c:catch var="error07">
    <c:import url="tt.txt" var="tt" scope="session" charEncoding="utf-8"></c:import>
</c:catch>
<c:out value="${error07}"></c:out><br>
<c:out value="import标签存储的tt字符串变量值为：${sessionScope.tt}"></c:out><br>

<c:out value="username是${param.username}"></c:out>
<c:out value="password是${param.password}"></c:out>
</body>
</html>