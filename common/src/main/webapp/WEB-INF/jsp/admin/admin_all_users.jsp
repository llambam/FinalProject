<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 29.01.2019
  Time: 19:41
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

    <title>ADMIN PAGE #1</title>
</head>
<body>

<button>
    <a href="adm_phones">Go to phone list</a>
</button>

<form action="FrontController" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Log out">
</form>


<form action="FrontController" method="post">
    <input type="hidden" name="command" value="admin_user_table">
    <input type="submit" value="View all users">
    <table class="table table-bordered">

        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Имя</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Логин</th>
            <th scope="col">Телефон</th>
            <th scope="col">e-mail</th>
            <th scope="col">Блокировка</th>
            <th scope="col">Дата регистрации</th>
            <th scope="col">Тип пользователя</th>
            <th scope="col">Нажми сюда</th>
        </tr>
        </thead>

        <c:forEach items="${userForAdminTableList}" var="userForAdminTable">

            <tr>
                <td>
                    <p>${userForAdminTable.userId}</p>
                </td>
                <td>
                    <p>${userForAdminTable.userName}</p>
                </td>
                <td>
                    <p>${userForAdminTable.surname}</p>
                </td>
                <td>
                    <p>${userForAdminTable.login}</p>
                </td>
                <td>
                    <p>${userForAdminTable.telephone}</p>
                </td>
                <td>
                    <p>${userForAdminTable.email}</p>
                </td>
                <td>
                    <p>${userForAdminTable.blocked}</p>
                </td>
                <td>
                    <p>${userForAdminTable.registrationDate}</p>
                </td>
                <td>
                    <p>${userForAdminTable.roleName}</p>
                </td>
                <td>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="userId" value="${userForAdminTable.userId}">
                        <input type="hidden" name="command" value="admin_go_to_edit">
                        <input type="submit" value="Edit">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
