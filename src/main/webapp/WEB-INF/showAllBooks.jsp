<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.library.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.library.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 10/2/2023
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View all books</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="wrapper">
    <form action="/books" method="post" class="tree-table-view">
        <!-- Display the list of books -->
        <h3>Books:</h3>
        <table>
            <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.bookName}
                    </td>
                    <td>${book.authorName}
                        <a href="/book/edit?bookId=${book.id}">Edit</a> | <a
                                href="/book/remove?bookId=${book.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="web-field">
            <a href="/my-account">Back</a><br>
            <a href="/assign">Assign book to user</a><br>
            <a href="/book/unassigned">UnAssign book</a><br>
        </div>
    </form>
</div>
</body>
</html>
