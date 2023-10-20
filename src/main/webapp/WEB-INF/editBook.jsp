<%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 10/4/2023
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admins dashboard</title>
  <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="wrapper">
  <form action="/book/edit" method="post">
    <div class="input-box">
      <input name="bookName" type="text" placeholder="Enter book's name" required>
    </div>
    <div class="input-box">
      <input name="authorName" type="text" placeholder="Enter book's author name" required>
    </div>
    <br>
    <div class="input-box button">
      <input type="submit" value="Edit">
    </div>
  </form>
  <div class="webcolor-field">
    <p><a href="/assign">Assign book to user</a></p>
    <p><a href="/add-book">Assign book to user</a></p>
    <p><a href="/">Back</a></p><br>
  </div>
</div>
</body>
</html>