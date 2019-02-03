<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <title>Insert title here</title>
</head>
<body>

<button>
    <a href="FrontController?command=test_command">View List </a>
</button>

<%--<button>--%>
<%--<%=request.getAttribute("testList")%>--%>
<%--</button>--%>





<table>
    <c:forEach items="${testList}" var="user">

        <tr>
            <td>
                <p>${user.userId}</p>
            </td>
            <td>
                <p>${user.userName}</p>
            </td>
            <td>
                <p>${user.surname}</p>
            </td>
            <td>
                <p>${user.login}</p>
            </td>
            <td>
                <form action="FrontController" method="post">
                    <input type="hidden" name="userId" value="${user.userId}">
                    <input type="hidden" name="command" value="user_block">
                    <input type="submit" value="Block">
                </form>
            </td>

            <td>
                <form action="FrontController" method="put">
                    <input type="hidden" name="userId" value="${user.userId}">
                    <input type="hidden" name="command" value="edit_user">
                    <input type="submit" value="Edit">
                </form>
            </td>

        </tr>
    </c:forEach>
</table>


</body>
</html>