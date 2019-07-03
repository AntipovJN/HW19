<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>${process}</title>
</head>
<body>
<form action="/${action}" method="POST">
    Login: <input type="text" name="login"/>
    Password: <input type="password" name="password"/>
   <c:if test="${action=='register'}">
            Password again: <input type="password" name="passwordRepeat"/>
   </c:if>
    <p>${isInvalid}</p>
    <input type="submit" value="${process}">
</form>

</body>
</html>


