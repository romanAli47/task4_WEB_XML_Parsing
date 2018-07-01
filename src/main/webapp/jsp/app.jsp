<%--
  Created by IntelliJ IDEA.
  User: Romanalikevich
  Date: 29.06.2018
  Time: 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <form action="/XMLServlet" method="post">
        <select id="parser type" name="parser" class="parser-selector">
            <option>DOM</option>
            <option>SAX</option>
            <option>StAX</option>
        </select>
        <input type="submit" name="parse_button" value="Start" class="nice-button"/>
    </form>
</div>
</body>
<style>
    .parser-selector {
        margin-top: 200px;
    }
</style>
</html>
