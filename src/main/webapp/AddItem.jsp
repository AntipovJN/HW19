<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${process}</title>
</head>
<body>
<H2>${isEmpty}
</H2>
<form action="${action}" method="post">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value="${name}"></td>
        </tr>
        <tr>
            <td>Link of img\photo</td>
            <td><input type="text" name="img" value="${img}"></td>
        </tr>
        <tr>
            <td>Price in UAH</td>
            <td><input type="number" name="price" value="${price}"></td>
        </tr>
        <tr>
            <input type="hidden" name="id" value="${id}">
        </tr>
    </table>
    <input type="submit" value="${process}">
</form>
</body>
</html>
