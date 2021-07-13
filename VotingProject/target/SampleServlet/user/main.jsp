<%@ page import="com.voting.model.User" %>
<!DOCTYPE html>
<html>
<body>
<center>
    <h1>
        USER - HOME
    </h1>
    <%
        User user = (User) request.getSession().getAttribute("user");
        out.println("Welcome, " + user.getLogin() + "<br>");
    %>

    <form method="post" action="/user/getUserThemes">
        <%
            out.println("<input type='hidden' name='userId' value=" + user.getId() + ">");
        %>

        <input type="submit" value="View active themes">
    </form>
    <form method="post" action="/user/getOldUserThemes">
        <%
            out.println("<input type='hidden' name='userId' value=" + user.getId() + ">");
        %>

        <input type="submit" value="View old themes">
    </form>
    <a href="../index.jsp">Logout</a>
</center>
</body>
</html>