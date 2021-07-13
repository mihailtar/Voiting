package com.voting.controller.user;

import com.voting.model.User;
import com.voting.service.UserService;
import com.voting.util.DatabaseConnect;
import com.voting.util.PasswordEncryption;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;


@WebServlet(name = "loginUser", urlPatterns = "/loginUser")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // принимаем параметры которые мы отправляем с формы
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            // подключаемся к базе данных
            Connection connection = DatabaseConnect.getConnection();
            // создаем экземпляр класса UserService ля дальнейшей работы с таблицей user
            UserService userService = new UserService(connection);
            // получаем пользователя с указанным логином
            User user = userService.getUser(login);
            // если такой пользователь существует, идем дальше
            if (user != null) {
                // проверяем верность пароля - для этого шифруем переданный параметр с формы и сравниваем с записью в базе данных
                if (PasswordEncryption.hashPassword(password, user.getSalt()).equals(user.getPassword())) {
                    // если пароли совпадают, передаем данного пользователя как параметр страницы
                    req.setAttribute("user", user);
                    // проверяем роль пользователя - для каждой роли должна загружаться своя страница
                    if (user.getRole().equals("Administrator")) {
                        // если пользователь - админ - записываем его данные в параметры сессии и отправляем на страницу admin/main.jsp
                        req.getSession().setAttribute("user", user);
                        RequestDispatcher view = req.getRequestDispatcher("admin/main.jsp");
                        view.forward(req, resp);
                    } else if (user.getRole().equals("User")) {
                        // если пользователь - пользователь - записываем его данные в параметры сессии и отправляем на страницу user/main.jsp
                        req.getSession().setAttribute("user", user);
                        RequestDispatcher view = req.getRequestDispatcher("user/main.jsp");
                        view.forward(req, resp);
                    } else {
                        // если вошел пользователь с какой то другой ролью - отправляем его на страницу ошибки с определенным текстом
                        req.setAttribute("message", "Unknown user's role");
                        RequestDispatcher view = req.getRequestDispatcher("error.jsp");
                        view.forward(req, resp);
                    }
                } else {
                    // если пароль не совпал - отправляем на страницу ошибки с соответствующим текстом
                    req.setAttribute("message", "Incorrect password");
                    RequestDispatcher view = req.getRequestDispatcher("error.jsp");
                    view.forward(req, resp);
                }
            } else {
                // если в базе данных не нашелся пользователь с указанным логином, также отправляем ошибку
                req.setAttribute("message", "Incorrect login");
                RequestDispatcher view = req.getRequestDispatcher("error.jsp");
                view.forward(req, resp);
            }
        } catch (Exception e) {
            // данный блок вызовется в случае возникновения непредвиденной ошибки
            System.err.println("----------------");
            System.err.println("ERROR - loginUser - " + e.getLocalizedMessage());
            System.err.println("----------------");
        }
    }
}
