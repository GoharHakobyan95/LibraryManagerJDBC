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
        <link rel="stylesheet" href="../css/style.css">
    </head>
<body>
<div class="wrapper">
    <c:if test="${not empty requestScope.successUnAssign}">
        <span style="color: green">${requestScope.successUnAssign}</span>
    </c:if>
    <form action="/book/unassigned" method="post">
        <div class="wrapper">
            <table>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>User_ID</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${assignedBooks}">
                <tr>
                    <td>${book.bookName}
                    </td>
                    <td>${book.authorName}
                    </td>
                    <td>${book.userId}
                    </td>
                    <td>
                        <input type="hidden" name="bookId" value="${book.id}">
                        <input type="submit" value="Unassign" class="input-box button">
                    </td>
                </tr>
                </tbody>
                </c:forEach>
            </table>
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
