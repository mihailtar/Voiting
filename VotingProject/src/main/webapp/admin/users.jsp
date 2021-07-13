<%@ page import="java.util.*" %>
<%@ page import="com.voting.model.User" %>
<!DOCTYPE html>
<html>
<body>
<center>
    <h1>
        USERS
    </h1>
    <%
        List result = (List) request.getAttribute("users");
        Iterator it = result.iterator();
        while (it.hasNext()) {
            User user = (User) it.next();
            out.println("Login: " + user.getLogin() + "<br>");
        }
    %>

    <a href="main.jsp">Home</a>
</center>
</body>
</html>