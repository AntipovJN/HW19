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
    <a href="/additem">
        <input type="submit" value="Add new item"/>
    </a>
</c:if>
<table>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.name}</td>
        </tr>
        <tr>
            <td>
                <img src="${item.img}" style="height: 200px; width: 200px"/>
            </td>
        </tr>
        <tr>
            <td>${item.price} UAH
            <c:if test="${userName}!='admin'"><input type="submit" value="Buy"></c:if> </td>
            <td>code: ${item.productCode}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
