<%@ page import="java.util.*" %>
<%@ page import="com.voting.model.Option" %>
<%@ page import="com.voting.model.User" %>
<%@ page import="com.voting.model.Statistic" %>
<!DOCTYPE html>
<html>
<body>
<center>
    <%
        List stats = (List) request.getAttribute("stats");
        String title = (String) request.getAttribute("title");
        out.println("<h1>" + title + "</h1>");
        Iterator it = stats.iterator();
        while (it.hasNext()) {
            Statistic statistic = (Statistic) it.next();
            out.println("Option title: " + statistic.getOption() + "<br>");
            out.println("Count votes: " + statistic.getCount() + "<br><br>");
        }
    %>
    <a href="main.jsp">Home</a>
</center>
</body>
</html>