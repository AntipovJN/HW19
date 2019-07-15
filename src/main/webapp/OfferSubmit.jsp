<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Offer submit</title>
</head>
<body>
We send code on your email to confirm the offer.
Please paste that into field

<h2>${isInvalidCode}</h2>
<form action="/offer" method="post">
    <input type="hidden" name="code" value="${code}">
    <input type="hidden" name="id" value="${id}">
    <input type="text" value="" name="codeFromForm"/>
    <input type="submit" value="Submit">
</form>
</body>
</html>
