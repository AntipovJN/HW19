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
<c:if test="${userName=='admin'}">
     <a href="/register">
         <input type="submit" value="Add new user"/>
     </a>
</c:if>
<a href="/items">
    List of Products
</a>
<table>
    <td>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.login}</td>
                <td>${user.password}</td>
            </tr>
        </c:forEach>
    </td>
</table>
<br>

</body>
</html>
