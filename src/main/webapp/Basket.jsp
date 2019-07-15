<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
${isEmptyBasket}
<c:forEach items="${items}" var="item">
    <br/>${item.name}    ${item.price} UAH
</c:forEach>
<c:if test="${isEmptyBasket==null}">
<br/> <a href="/offer?id=${id}">
    <button>Submit offer</button>
</a>
</c:if>
</body>
</html>
