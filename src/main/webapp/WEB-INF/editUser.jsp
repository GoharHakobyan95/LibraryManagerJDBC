<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.library.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 10/3/2023
  Time: 11:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Update user's data </title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<c:set var="user" value="${requestScope.user}"/>
<div class="wrapper">
    <h2>Edit</h2>
    <form action="/user/edit" method="post">
        <div class="input-box">
            <input type="hidden" name="userId" value="${user.id}">
            <input name="name" type="text" placeholder="Enter your name" value="${user.name}" required>
        </div>
        <div class="input-box">
            <input name="lastName" type="text" placeholder="Enter your last name" value="${user.lastName}"
                   required>
        </div>
        <c:if test="${not empty requestScope.usernameExistsErr}">
            <span style="color: red">${requestScope.successAssign}</span></c:if>


        <c:if test="${not empty requestScope.emailFormatErr}">
            <span style="color: red">${requestScope.emailFormatErr}</span></c:if>

        <div class="input-box">
            <input name="email" type="text" placeholder="Enter your email" value="${user.email}" required>
        </div>
        <c:if test="${not empty requestScope.passwordErrMsg}">
            <span style="color: red">${requestScope.passwordErrMsg}</span></c:if>

        <div class="input-box button">
            <input type="Submit" value="Update user's data">
        </div>
        <div class="text">
            <h3><a href="/">Login now</a></h3>
        </div>
    </form>
</div>

</body>
</html>
