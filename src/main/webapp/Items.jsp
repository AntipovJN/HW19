<%@ page import="java.util.List" %>
<%@ page import="entity.Item" %>
<%@ page import="factory.serviceFactories.ItemServiceFactory" %>
<%@ page import="factory.serviceFactories.SessionServiceFactory" %>
<%@ page import="services.interfaces.SessionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    SessionService sessionService = SessionServiceFactory.getInstance();
    List<Item> items = ItemServiceFactory.getInstance().getAll();
%>
<html>
<head>
    <title>Items</title>
</head>
<body>
<% if (sessionService.isAdmin(request)) {
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
        <td>
            <% if (sessionService.isAdmin(request)) {%>
            <%="<a href = \"/items/remove?id=" + item.getId() + "\"> remove item</a>" %><%}%>
        </td>
        <td>
            <% if (sessionService.isAdmin(request)) {%>
            <%="<a href = \"/items/edit?id=" + item.getId() + "\"> change item</a>"%><%}%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
