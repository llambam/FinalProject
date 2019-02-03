<%@ page language="java" contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<div align="center">
    <div>
        <form action="FrontController" id="form-page-registration">
            <input type="hidden" name="command" value="registration">
            <div class="table">
                <div class="tr">
                    <div class="login">
                        <input type="text" id="Login" name="login" size="20" maxlength="25" placeholder="Login">
                    </div>
                </div>
                <div class="tr">
                    <div class="password">
                        <input type="password" id="Password" name="password" size = "20" maxlength="25"  placeholder="Password">
                    </div>
                </div>
                <div class="tr">
                    <div class="name">
                        <input type="text" id="Name" name="name" size = "20" maxlength="25"  placeholder="Name">
                    </div>
                </div>
                <div class="tr">
                    <div class="surname">
                        <input type="text" id="Surname" name="surname" size = "20" maxlength="25"  placeholder="Surname">
                    </div>
                </div>
                <div class="tr">
                    <div class="telephone">
                        <input type="text" id="Telephone" name="telephone" size = "20" maxlength="25"  placeholder="Telephone">
                    </div>
                </div>
                <div class="tr">
                    <div class="date">
                        <input type="text" id="Date" name="date" size = "20" maxlength="25"  placeholder="Date">
                    </div>
                </div>
                <div class="tr">
                    <div class="email">
                        <input type="text" id="Email" name="email" size = "20" maxlength="25"  placeholder="Email">
                    </div>
                </div>
            </div>
            <input type="submit" id="finish_button" value="next->">
        </form>
    </div>
</div>
</body>
