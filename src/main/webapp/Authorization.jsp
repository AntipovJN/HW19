<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>${process}</title>
</head>
<body>
<form action="/${action}" method="POST">
    <input type="hidden" name="id" value="${id}">
    Login: <input type="text" name="login" value="${login}"/>
    Password: <input type="password" name="password"/>
    <c:if test="${action=='users/edit' or action=='register'}">
        Password again: <input type="password" name="passwordRepeat"/>
    </c:if>
    <p>${isInvalid}
    </p>
    <input type="submit" value="${process}">
</form>
</body>
</html>


