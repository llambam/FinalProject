<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 05.02.2019
  Time: 19:35
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

    <title>Add new number</title>
</head>
<body>

<form action="FrontController" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Log out">
</form>


<form action="FrontController" id="form-add-new-number">
    <input type="hidden" name="command" value="add_new_number">
    <div class="table">

        <div class="tr">
            <div class="name">
                <input type="text" id="Name" name="name" size="20" maxlength="25" placeholder="Name">
            </div>
        </div>
        <div class="tr">
            <div class="surname">
                <input type="text" id="Surname" name="surname" size="20" maxlength="25" placeholder="Surname">
            </div>
        </div>
        <div class="tr">
            <div class="telephone">
                <input type="text" id="Telephone" name="telephone" size="20" maxlength="25" placeholder="Telephone">
            </div>
        </div>

        <div class="tr">
            <div class="email">
                <input type="text" id="Email" name="email" size="20" maxlength="25" placeholder="Email">
            </div>
        </div>


        <div class="tr">
            <div class="City">
                <input type="text" id="City" name="city" size="20" maxlength="25" placeholder="City">
            </div>
        </div>
        <div class="tr">
            <div class="District">
                <input type="text" id="District" name="district" size="20" maxlength="25" placeholder="District">
            </div>
        </div>
        <div class="tr">
            <div class="Street">
                <input type="text" id="Street" name="street" size="20" maxlength="25" placeholder="Street">
            </div>
        </div>
        <div class="tr">
            <div class="HouseNumb">
                <input type="text" id="HouseNumb" name="house_number" size="20" maxlength="25"
                       placeholder="House Number">
            </div>
        </div>
        <div class="tr">
            <div class="Floor">
                <input type="text" id="Floor" name="floor" size="20" maxlength="25" placeholder="Floor">
            </div>
        </div>
        <div class="tr">
            <div class="apartmentNumber">
                <input type="text" id="apartmentNumber" name="apartment_number" size="20" maxlength="25"
                       placeholder="Apartment Number">
            </div>
        </div>


    </div>
    <input type="submit" id="finish_button" value="Add ">
</form>

<button>
    <a href="main_table">Back</a>
</button>

</body>
</html>
