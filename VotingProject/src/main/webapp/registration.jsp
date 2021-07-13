<%@ page import="java.util.*" %>
<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<body>
<center>
    <h1>
        REGISTRATION
    </h1>
    <%
        String result = (String) request.getAttribute("result");
        out.println(result);
    %>
    <br>
    <a href="index.jsp">Return to main page</a>
</center>
</body>
</html>