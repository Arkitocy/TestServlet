import Bean.User;
import Service.UserService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CheckUserNameServlet")
public class CheckUserNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        UserService us = new UserService();
        boolean rs = false;
        User user = us.findByName(name);
        if (user==null) {
            rs=false;
        }else {
            rs=true;
        }
        Map rsmap = new HashMap();
        rsmap.put("rs",rs);
        String rs1 = JSONObject.toJSONString(rsmap);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().append(rs1);
    }
}
