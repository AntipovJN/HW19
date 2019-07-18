<%@ page import="factory.serviceFactories.AccountServiceFactory" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    User user = AccountServiceFactory.getAccountServiceImpl().getUser();
    List<User> users = AccountServiceFactory.getAccountServiceImpl().getAll();
%>
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
<% if (user.getLogin().equals("admin")) {
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
        </tr>
        <%}%>
    </td>
</table>
</body>
</html>
