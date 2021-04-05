<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Beans.Jobs" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yzhim
  Date: 2021/3/3
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <c:set scope="application" var = "key" value = "3"> </c:set>
  <c:if test="${1!=2}">
    <h>123</h>
  </c:if>
  <c:choose>
    <c:when  test = "${applicationScope.key > 4}">
      <h1>1</h1>
    </c:when>
    <c:otherwise>
      <h1>2</h1>
    </c:otherwise>
  </c:choose>
  <c:set scope="page" var = "key1" value = "value1"></c:set>
  ${pageScope.key1}
  ${applicationScope.key}

  <%
    request.setAttribute("a",new String[]{"123","123","123"});
  %>
  <c:forEach items="${requestScope.a}" var = "e">
    <h3>${e}</h3>
  </c:forEach>
  <%
    Map<String,String> map = new HashMap<>();
    map.put("a","123");
    map.put("b","234");
    request.setAttribute("b",map);
  %>
  <c:forEach items="${requestScope.b}" var="entry">
    <h1>
      ${entry.key} : ${entry.value}
    </h1>
  </c:forEach>
  <%
    List<Jobs> list = new ArrayList<Jobs>();
    for (int i = 0; i < 10; i++){
      list.add(new Jobs(" "+ i,"123" + i,2 + i,123 + i));
    }
    request.setAttribute("c",list);
  %>
  <table>
    <tr>
    <th>
      job_id
    </th>
    <th>
      job_title
    </th>
    </tr>
      <c:forEach items="${requestScope.c}" var = "el">
        <tr>
        <td>${el.job_id}</td>
        <td>${el.job_title}</td>
        </tr>
      </c:forEach>
  </table>
  </body>
</html>