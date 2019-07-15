<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Items</title>
</head>
<body>
<br/>
<a href="/user/basket">
    <input type="submit" value="Basket">
</a>
<c:if test="${isAdmin==true}">
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
            <c:choose>
                <c:when test="${isAdmin==true}">
                    <td><a href="/items/edit?id=${item.id}"> change item</a></td>
                    <td><a href="/items/remove?id=${item.id}"> remove item</a></td>
                </c:when>
                <c:otherwise>
                    <td>
                        <a href="/addToBasket?id=${item.id}">
                            <button>Add to basket</button>
                        </a>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>