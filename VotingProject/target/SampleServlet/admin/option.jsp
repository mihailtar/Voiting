<%@ page import="com.voting.model.Option" %>
<!DOCTYPE html>
<html>
<body>
<center>
    <h1>
        EDIT OPTION
    </h1>
    <h3>Edit</h3>
    <form action="editOption" method="post">
        <%
            Option option = (Option) request.getAttribute("option");
            int themeId = (int) request.getAttribute("themeId");
            out.println("<input type='hidden' name='themeId' value=" + themeId + "><br>");
            out.println("<input type='hidden' name='optionId' value=" + option.getId() + "><br>");
            out.println("<input type='text' name='title' value=" + option.getTitle() + "><br>");
        %>
        <input type="submit" value="Save">
    </form>
</center>
</body>
</html>