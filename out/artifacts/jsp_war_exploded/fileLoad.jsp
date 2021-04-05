<%--
  Created by IntelliJ IDEA.
  User: yzhim
  Date: 2021/3/16
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testFileLoad</title>
</head>
<body>
<form method="post" action="testFile" enctype="multipart/form-data">
    请输入用户名 ： <input type = "text" name = "name"/> <br>
    <input type="file" name = "file">
    <input type = "submit">
</form>
</body>
</html>
