<%@ page import="java.util.*" %>
<%@ page import="com.voting.model.User" %>
<!DOCTYPE html>
<html>
<body>
<center>
    <h1>
        ADMINISTRATOR - HOME
    </h1>
    <%
        User user = (User) request.getSession().getAttribute("user");
        out.println("Welcome, " + user.getLogin() + "<br>");

    %>

    <form method="post" action="/admin/getThemes">
        <input type="submit" value="View themes">
    </form>
    <form method="post" action="/admin/getUsers">
        <input type="submit" value="View users">
    </form>
    <a href="../index.jsp">Logout</a>
</center>
</body>
</html>