<%@ page import="static sun.misc.MessageUtils.out" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String action = request.getAttribute("action") != null ? request.getAttribute("action").toString() : "";
    String process = request.getAttribute("process") != null ? request.getAttribute("process").toString() : "";
    String login = request.getAttribute("login") != null ? request.getAttribute("login").toString() : "";
    String isInvalid = request.getAttribute("isInvalid") != null ? request.getAttribute("isInvalid").toString() : "";
    String items = "";
    if (action.equals("register")) {
        items = (" Password again: <input type=\"password\" name=\"passwordRepeat\"/>");
    }%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><%=process%>
    </title>
</head>
<body>
<form action="/<%=action%>" method="POST">
    Login: <input type="text" name="login" value="<%=login%>"/>
    Password: <input type="password" name="password"/>
    <%=items%>
    <p><%=isInvalid%>
    </p>
    <input type="submit" value="<%=process%>">
</form>

</body>
</html>


