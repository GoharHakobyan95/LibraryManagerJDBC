<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.library.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.library.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 10/2/2023
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Assign book to user</title>
        <c:set var="users" value="${sessionScope.users}"/>
        <c:set var="unassignedBooks" value="${sessionScope.unassignedBooks}"/>
        <link rel="stylesheet" href="css/style.css">
        <h2>Admin</h2>
    </head>
<body>
<div class="wrapper">
    <form action="/assign" method="post">
        <c:if test="${not empty requestScope.successAssign}">
            <span style="color: green">${requestScope.successAssign}</span>
        </c:if>
        <div class="input-box">
            <label for="selectuser"> Select User:
                <select name="selectedUser" id="selectuser">
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}"> ${user.name } ${user.lastName}</option>
                    </c:forEach>
                </select>
            </label>
        </div>

        <br>
        <div class="input-box">
            <label for="selectbook"> Select Book:
                <select name="selectedBook" id="selectbook">
                    <c:forEach var="book" items="${sessionScope.unassignedBooks}">
                        <option value="${book.id}">${book.bookName } ${book.authorName}</option>
                    </c:forEach>
                </select>
            </label>
        </div>

        <br>
        <div class="input-box button">
            <input type="submit" value="Assign">
        </div>
    </form>

    <ul>
        <li><a href="/">Back</a>
        </li>
        <li><a href="/users">Show all users</a>
        </li>
        <li><a href="/books">Show all books</a>
        </li>
    </ul>
</div>
</body>
</html>
