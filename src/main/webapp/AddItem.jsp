<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String isEmpty = request.getAttribute("isEmpty") != null ? request.getAttribute("isEmpty").toString() : "";
%>
<html>
<head>
    <title>AddItem</title>
</head>
<body>
<H2><%=isEmpty%>
</H2>
<form action="/additem" method="post">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Link of img\photo</td>
            <td><input type="text" name="img"></td>
        </tr>
        <tr>
            <td>Price in UAH</td>
            <td><input type="number" name="price"></td>
        </tr>
    </table>
    <input type="submit" value="add">
</form>
</body>
</html>
