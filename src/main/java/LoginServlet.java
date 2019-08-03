import Bean.User;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String pwd = request.getParameter("userpwd");
        String type = request.getParameter("type");
        UserService us = new UserService();
        User user=us.loginUser(name, pwd,type);
        if (user!=null && user.getType().equals("teacher")) {
            response.sendRedirect("index.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("username", name);
            session.setAttribute("userid",user.getId());
            session.setAttribute("permission","teacher");

        }else if(user!=null && user.getType().equals("student")){
            response.sendRedirect("index.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("username", name);
            session.setAttribute("userid",user.getId());
            session.setAttribute("permission","student");
        }
        else {
            response.sendRedirect("login.html");
        }
    }
}
