<%@ page import="com.voting.model.Theme" %>
<!DOCTYPE html>
<html>
<body>
<center>
    <h1>
        EDIT THEME
    </h1>
    <h3>Edit</h3>
    <form action="editTheme" method="post">
        <%
            Theme theme = (Theme) request.getAttribute("theme");
            out.println("<input type='hidden' name='themeId' value=" + theme.getId() + "><br>");
            out.println("<input type='text' name='title' value=" + theme.getTitle() + "><br>");
            out.println("<input type='checkbox' name='active' value=" + theme.isActive() + ">Active<br>");
        %>
        <input type="submit" value="Save">
    </form>
</center>
</body>
</html>