<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 03.02.2019
  Time: 10:09
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

    <title>ADMIN PAGE #2</title>
</head>
<body>

<button>
    <a href="adm_users">Go to user list</a>
</button>

<form action="FrontController" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Log out">
</form>


<form action="FrontController" method="post">
    <input type="hidden" name="command" value="admin_phones_table">
    <input type="submit" value="View all phones">
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
            <th scope="col">Улица</th>
            <th scope="col">Дом</th>
            <th scope="col">Этаж</th>
            <th scope="col">Квартира</th>
            <th scope="col">Дата создания</th>
            <th scope="col">Edit</th>
        </tr>
        </thead>

        <c:forEach items="${bookForAdminLinkedList}" var="bookForAdminLinkedList">

            <tr>
                <td>
                    <p>${bookForAdminLinkedList.phoneBookId}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.userID}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.name}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.surname}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.telephone}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.eMail}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.city}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.district}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.street}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.houseNumber}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.floor}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.apartmentNumber}</p>
                </td>
                <td>
                    <p>${bookForAdminLinkedList.creationDate}</p>
                </td>
                <td>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="phoneBookId" value="${bookForAdminLinkedList.phoneBookId}">
                        <input type="hidden" name="command" value="admin_go_to_edit_phones">
                        <input type="submit" value="Edit">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
