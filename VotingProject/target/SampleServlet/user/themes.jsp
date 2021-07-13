<%@ page import="java.util.*" %>
<%@ page import="com.voting.model.Theme" %>
<!DOCTYPE html>
<html>
<body>
<center>
    <h1>
        THEMES LIST
    </h1>
    <%
        List themes = (List) request.getAttribute("themes");
        Iterator it = themes.iterator();
        while (it.hasNext()) {
            Theme theme = (Theme) it.next();
            out.println("Title: " + theme.getTitle() +
                    "<form action='/user/getUserOptions' method='post'>" +
                    "<input type='hidden' name='themeId' value=" + theme.getId() + ">" +
                    "<input type='submit' value='Show options'>" +
                    "</form>" +
                    "<form action='/user/getStats' method='post'>" +
                    "<input type='hidden' name='themeId' value=" + theme.getId() + ">" +
                    "<input type='hidden' name='title' value='" + theme.getTitle() + "'>" +
                    "<input type='submit' value='Show stats'>" +
                    "</form>" +
                    "<br>");
        }
    %>
    <a href="main.jsp">Home</a>
</center>
</body>
</html>