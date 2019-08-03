import Bean.User;
import Comment.KeyUtils;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService us = new UserService();
        String name = request.getParameter("username");
        String pwd = request.getParameter("userpwd");
        String type = request.getParameter("type");
        String classes = request.getParameter("class");
        if (request.getParameter("userage").equals("")) {
            response.sendRedirect("regist.html");
        } else {
            int age = Integer.parseInt(request.getParameter("userage"));
//        System.out.println("username"+name);
//        System.out.println("userpwd"+pwd);
            User user = us.registUser(name, pwd, age, type, classes);
            if (user==null) {
                response.sendRedirect("regist.html");
            } else {
                response.sendRedirect("login.html");
            }
        }

    }
}
