<%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 10/13/2023
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
    <h2>Welcome <c:out value="${sessionScope.user.name}"/></h2>
</head>
<body>
<div class="wrapper">
    <form action="/my-account/user" method="get">
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
            <c:forEach var="book" items="${userAllBooks}">
                    <tr>
                        <td>${book.bookName}</td>
                        <td>${book.authorName}</td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
    <div class="web-field">
        <a href="/">Back</a><br>
    </div>
</div>
</body>
</html>
