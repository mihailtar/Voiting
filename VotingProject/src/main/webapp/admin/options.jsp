<%@ page import="java.util.*" %>
<%@ page import="com.voting.model.Option" %>
<!DOCTYPE html>
<html>
<body>
<center>
    <h1>
        OPTIONS LIST
    </h1>
    <%
        int themeId = (int) request.getAttribute("themeId");
        List options = (List) request.getAttribute("options");
        Iterator it = options.iterator();
        while (it.hasNext()) {
            Option option = (Option) it.next();
            out.println("Title: " + option.getTitle() + "<br>");
            out.println("<form action='/admin/deleteOption' method='post'>" +
                    "<input type='hidden' name='optionId' value=" + option.getId() + ">" +
                    "<input type='hidden' name='themeId' value=" + themeId + ">" +
                    "<input type='submit' value='Delete option'></form>" +
                    "<form action='/admin/getOption' method='post'>" +
                    "<input type='hidden' name='optionId' value=" + option.getId() + ">" +
                    "<input type='hidden' name='themeId' value=" + themeId + ">" +
                    "<input type='submit' value='Edit option'></form>");
        }
    %>
    <h3>Add new option</h3>
    <form action="addOption" method="post">
        Title: <input type="text" name="title"/>
        <%
            out.println("<input type='hidden' name='themeId' value=" + themeId + ">");
        %>
        <br>
        <input type="submit" value="Add">
    </form>

    <a href="main.jsp">Home</a>
</center>
</body>
</html>