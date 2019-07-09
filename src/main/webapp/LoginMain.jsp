<%@ page import="factory.serviceFactories.UserServiceFactory" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="services.interfaces.SessionService" %>
<%@ page import="factory.serviceFactories.SessionServiceFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    SessionService sessionService = SessionServiceFactory.getInstance();
    List<User> users = UserServiceFactory.getUserServiceImpl().getAll();
%>
<html>
<head>
    <title>Main</title>
</head>
<body>
<H1>Hello <%sessionService.getUserFromSession(request).get().getLogin(); %></H1>
<br/>
<a href="/exit">
    <input type="submit" value="End session">
</a>
<% if (sessionService.isAdmin(request)) {
    response.getWriter().write("<a href=\"/register\">" +
            "<input type=\"submit\" value=\"Add User\"/></a>");
}%>
<a href="/items">
    List of Products
</a>
<table>
    <td>
        <% for (User currentUser : users) { %>
        <tr>
            <td><%=currentUser.getLogin()%>
            </td>
            <td><%=currentUser.getPassword()%>
            </td>
            <td>
                <% if (sessionService.isAdmin(request)) {%>
                <%="<a href = \"/users/remove?id=" + currentUser.getId() + "\"> remove user</a>" %><%}%>
            </td>
            <td>
                <% if (sessionService.isAdmin(request)) {%>
                <%="<a href = \"/users/change?id=" + currentUser.getId() + "\"> change user</a>"%><%}%>
            </td>
        </tr>
        <%}%>
    </td>
</table>
</body>
</html>
