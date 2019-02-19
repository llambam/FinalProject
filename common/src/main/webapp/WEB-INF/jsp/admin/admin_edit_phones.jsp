<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 10.02.2019
  Time: 13:38
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

    <title>ADMIN PAGE #4</title>
</head>
<body>

<button>
    <a href="adm_phones">Back</a>
</button>
<form action="FrontController" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Log out">
</form>


<form action="FrontController" method="post">
    <input type="hidden" name="command" value="admin_show_user">
    <input type="submit" value="View user">
    <table class="table table-bordered">

        <thead>
        <tr>
            <th scope="col">ID телефона</th>
            <th scope="col">ID пользователя</th>
            <th scope="col">Имя</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Телефон</th>
            <th scope="col">e-mail</th>
            <th scope="col">Город</th>
            <th scope="col">Район</th>
            <th scope="col">Дом</th>
            <th scope="col">Этаж</th>
            <th scope="col">Квартира</th>
            <th scope="col">Дата создания</th>
        </tr>
        </thead>

        <tr items="${bookForAdminLinkedList}" var="bookForAdminLinkedList">
            <td>
                <p>${bookForAdminLinkedList.userId}</p>
            </td>
            <td>
                <p>${bookForAdminLinkedList.userName}</p>
            </td>
            <td>
                <p>${bookForAdminLinkedList.surname}</p>
            </td>
            <td>
                <p>${bookForAdminLinkedList.login}</p>
            </td>
            <td>
                <p>${bookForAdminLinkedList.password}</p>
            </td>
            <td>
                <p>${bookForAdminLinkedList.telephone}</p>
            </td>
            <td>
                <p>${bookForAdminLinkedList.email}</p>
            </td>
            <td>
                <p>${bookForAdminLinkedList.blocked}</p>
            </td>
            <td>
                <p>${bookForAdminLinkedList.registrationDate}</p>
            </td>
            <td>
                <p>${bookForAdminLinkedList.roleName}</p>
            </td>
        </tr>
    </table>
</form>

<form action="FrontController">
    <input type="hidden" name="command" value="edit_user_for_admin">
    <input type="submit" value="Edit user">
    <table class="table table-bordered">
        <tr>
            <td>
                <input type="text" name="userName" size="20" maxlength="25" placeholder="Имя">
            </td>
            <td>
                <input type="text" name="surname" size="20" maxlength="25" placeholder="Фамилия">
            </td>
            <td>
                <input type="text" name="login" size="20" maxlength="25" placeholder="Логин">
            </td>
            <td>
                <input type="text" name="password" size="20" maxlength="25" placeholder="Пароль">
            </td>
            <td>
                <input type="text" name="telephone" size="20" maxlength="25" placeholder="Телефон">
            </td>
            <td>
                <input type="text" name="email" size="20" maxlength="25" placeholder="e-mail">
            </td>

        </tr>

    </table>
</form>

<%--<form action="FrontController" method="post">--%>
    <%--<input type="hidden" name="command" value="block">--%>
    <%--<input type="submit" value="Block user">--%>
<%--</form>--%>


<%--<form action="FrontController" method="post">--%>
    <%--<input type="hidden" name="command" value="change_status">--%>
    <%--<input type="submit" value="Change status">--%>
<%--</form>--%>

<%--<form action="FrontController">--%>
    <%--<input type="hidden" name="command" value="delete_user">--%>
    <%--<input type="submit" value="Delete user">--%>
<%--</form>--%>


</body>
</html>
