<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.library.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.library.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 10/1/2023
  Time: 7:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admins dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
    <h2> Welcome <c:set var="user" value="${sessionScope.user.name}"/><c:out value="${user}"/></h2>
</head>
<body>
<div class="wrapper">
    <form action="/add-book" method="post">
        <c:if test="${not empty requestScope.bookAddingMsg}">
            <span style="color: green">${requestScope.bookAddingMsg}</span>
        </c:if>
        <div class="input-box">
            <input name="bookName" type="text" placeholder="Enter book's name" required>
        </div>
        <div class="input-box">
            <input name="authorName" type="text" placeholder="Enter book's author name" required>
        </div>
        <br>
        <div class="input-box button">
            <input type="submit" value="Add">
        </div>
    </form>
    <div class="webcolor-field">
        <p><a href="/users">Show all users</a></p>
        <p><a href="/books">Show all books</a></p>
        <p><a href="/assign">Assign book to user</a></p>
        <p><a href="/">Back</a></p><br>
    </div>
</div>
</body>
</html>
