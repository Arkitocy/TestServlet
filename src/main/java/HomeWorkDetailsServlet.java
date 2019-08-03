import Bean.Homework;
import Bean.HomeworkDetail;
import Service.HomeworkService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomeWorkDetailsServlet")
public class HomeWorkDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomeworkService hs = new HomeworkService();
        String hid=request.getParameter("hid");
        Homework hw = hs.getHomeworkById(hid);
        ArrayList<HomeworkDetail> arrayList = hs.showByHid(hw);
        String rs = JSONObject.toJSONString(arrayList);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().append(rs);
    }
}
