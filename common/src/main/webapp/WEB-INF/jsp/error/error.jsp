<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 02.02.2019
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <title>ERROR</title>
</head>
<body>
<p style="font-size: 30px;px">ОШИБКА!</p></body>

<form action="FrontController" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="На главную">
</form>

</html>
