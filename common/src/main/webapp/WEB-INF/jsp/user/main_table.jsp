<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 02.02.2019
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <title>Main table</title>
</head>
<body>

<form action="FrontController" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Log out">
</form>

<button>
    <a href="add_new_number">Add new</a>
</button>

<form action="FrontController" method="post">
    <input type="hidden" name="command" value="phonebooktable">
    <input type="submit" value="View all telephone book">

<table class="table table-bordered">

    <thead>
    <tr>
        <th scope="col">Имя</th>
        <th scope="col">Фамилия</th>
        <th scope="col">Телефон</th>
        <th scope="col">e-mail</th>
        <th scope="col">Добавить в избранное</th>
    </tr>
    </thead>

    <c:forEach items="${phoneBookList}" var="phoneBook">

        <tr>
            <td>
                <p>${phoneBook.name}</p>
            </td>
            <td>
                <p>${phoneBook.surname}</p>
            </td>
            <td>
                <p>${phoneBook.telephone}</p>
            </td>
            <td>
                <p>${phoneBook.eMail}</p>
            </td>
            <td>
            <form action="FrontController" method="post">
                <input type="hidden" name="phoneBookId" value="${phoneBook.phoneBookId}">
                <input type="hidden" name="command" value="add_to_favorite">
                <input type="submit" value="Add">
            </form>
            </td>
        </tr>
    </c:forEach>
</table>




</form>
</body>
</html>
