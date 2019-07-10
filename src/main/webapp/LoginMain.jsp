<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<H1>Hello ${userName}</H1>
<br/>
<a href="/exit">
    <input type="submit" value="End session">
</a>
<c:if  test="${isAdmin==true}">
    <a href="/register">
            <input type="submit" value="Add User"/>
    </a>
</c:if>
<a href="/items">
    List of Products
</a>
<table>
    <td>
        <c:forEach items="${users}" var="user" >
        <tr>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>
                <c:if test="${isAdmin==true}">
                <a href = "/users/remove?id=${user.id}">
                    remove user
                </a>
            </td>
            <td>
                <a href = "/users/edit?id=${user.id}">
                        change user
                    </a>"
                </c:if>
            </td>
        </tr>
    </td>
    </c:forEach>
</table>
</body>
</html>
