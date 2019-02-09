<%@ page language="java" contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <meta charset="UTF-8">
    <title>Registration Step 1</title>
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
                    <div class="email">
                        <input type="text" id="Email" name="email" size = "20" maxlength="25"  placeholder="Email">
                    </div>
                </div>


                <div class="tr">
                    <div class="City">
                        <input type="text" id="City" name="city" size="20" maxlength="25" placeholder="City">
                    </div>
                </div>
                <div class="tr">
                    <div class="District">
                        <input type="text" id="District" name="district" size = "20" maxlength="25"  placeholder="District">
                    </div>
                </div>
                <div class="tr">
                    <div class="Street">
                        <input type="text" id="Street" name="street" size = "20" maxlength="25"  placeholder="Street">
                    </div>
                </div>
                <div class="tr">
                    <div class="HouseNumb">
                        <input type="text" id="HouseNumb" name="house_number" size = "20" maxlength="25"  placeholder="House Number">
                    </div>
                </div>
                <div class="tr">
                    <div class="Floor">
                        <input type="text" id="Floor" name="floor" size = "20" maxlength="25"  placeholder="Floor">
                    </div>
                </div>
                <div class="tr">
                    <div class="apartmentNumber">
                        <input type="text" id="apartmentNumber" name="apartment_number" size = "20" maxlength="25"  placeholder="Apartment Number">
                    </div>
                </div>


            </div>
            <input type="submit" id="finish_button" value="REGISTER">
        </form>
    </div>
</div>
</body>
