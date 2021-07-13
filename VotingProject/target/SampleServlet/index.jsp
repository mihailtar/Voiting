<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Voting System</title>
</head>
<body>
<center>
    <%
        request.getSession().setAttribute("user", null);
    %>
    <h1>
        VOTING SYSTEM
    </h1>
    <a href="registr.html">Registration</a>
    <form method="post" action="loginUser">
        Login: <input type="text" name="login"/><br>
        Password: <input type="password" name="password"/><br>
        <input type="submit" value="Login">
    </form>
</center>


</body>
</html>