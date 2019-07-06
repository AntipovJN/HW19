<%@ page import="entity.User" %>
<%@ page import="factory.serviceFactories.AccountServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Item" %>
<%@ page import="factory.serviceFactories.ItemServiceFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    User user = AccountServiceFactory.getAccountServiceImpl().getUser();
    List<Item> items = ItemServiceFactory.getItemServiceImpl().getAll();
%>
<html>
<head>
    <title>Items</title>
</head>
<body>
<% if (user.getLogin().equals("admin")) {
    response.getWriter().write("<a href=\"/additem\">" +
            "<input type=\"submit\" value=\"Add new item\"/></a>");
}%>
<table>
    <% for (Item item : items) { %>(
    <tr>
        <td><%=item.getName()%>
        </td>
    </tr>
    <tr>
        <td><img src="<%=item.getImg()%>" style="height:200px; width:200px"/>
        </td>
    </tr>
    <tr>
        <td><%=item.getPrice()%> UAH</td>
        <td> code: <%=item.getProductCode()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
