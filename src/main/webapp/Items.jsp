<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Items</title>
</head>
<body>
<c:if  test="${isAdmin==true}">
    <a href="/additem">
        <input type="submit" value="Add Item"/>
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
            <td>${item.price} UAH</td>
            <td>code: ${item.productCode}</td>
            <c:if  test="${isAdmin==true}">
            <td><a href = "/items/edit?id=${item.id}"> change item</a></td>
            <td><a href = "/items/remove?id=${item.id}"> remove item</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>