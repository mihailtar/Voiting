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
            out.println("Title: " + theme.getTitle() + "; Active: " + theme.isActive() + "" +
                    "<form action='/admin/getOptions' method='post'><input type='hidden' name='themeId' value=" + theme.getId() + "><input type='submit' value='Show options'></form>" +
                    "<form action='/admin/getTheme' method='post'><input type='hidden' name='themeId' value=" + theme.getId() + "><input type='submit' value='Edit theme'></form>" +
                    "<form action='/admin/deleteTheme' method='post'><input type='hidden' name='themeId' value=" + theme.getId() + "><input type='submit' value='Delete theme'></form>" +
                    "<br>");
        }
    %>
    <h3>Add new theme</h3>
    <form action="addTheme" method="post">
        Title: <input type="text" name="title"/><br>
        <input type="submit" value="Add">
    </form>

    <a href="main.jsp">Home</a>
</center>
</body>
</html>