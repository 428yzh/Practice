<%--
  Created by IntelliJ IDEA.
  User: yzhim
  Date: 2021/3/28
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录页面</title>
  </head>
  <body>
  <%
    Cookie[] cookie = request.getCookies();
    for (Cookie cookie1 : cookie){
      if ("name".equals(cookie1.getName())){
        request.setAttribute("name",cookie1.getValue());

      }
    }
  %>
  <form action="loginServlet" method="post">
    <input name="name" value="${requestScope.name}"/>
    <input name="passwd" type="password"/>
    <input type = "submit" value="提交" />
  </form>
  </body>
</html>
