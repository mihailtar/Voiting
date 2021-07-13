<%@ page import="java.util.*" %>
<%@ page import="com.voting.model.Option" %>
<%@ page import="com.voting.model.User" %>
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
        User user = (User) request.getSession().getAttribute("user");
        Iterator it = options.iterator();
        while (it.hasNext()) {
            Option option = (Option) it.next();
            out.println("Title: " + option.getTitle() + "<br>");
            out.println("<form action='/user/addAnswer' method='post'>" +
                    "<input type='hidden' name='optionId' value=" + option.getId() + ">" +
                    "<input type='hidden' name='themeId' value=" + themeId + ">" +
                    "<input type='hidden' name='userId' value=" + user.getId() + ">" +
                    "<input type='submit' value='Choose'></form>");
        }
    %>
    <a href="main.jsp">Home</a>
</center>
</body>
</html>