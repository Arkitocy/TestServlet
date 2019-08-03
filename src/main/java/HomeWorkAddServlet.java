import Bean.Homework;
import Comment.KeyUtils;
import Service.HomeworkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(name = "HomeWorkAddServlet")
public class HomeWorkAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomeworkService hs = new HomeworkService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String title = request.getParameter("title");
        String details = request.getParameter("details");
        String seldate = request.getParameter("seldate");
        String type = request.getParameter("type");
        try {
            Homework hw = new Homework();
            String id = KeyUtils.genUniqueKey();
            hw.setFinish_time(sdf.parse(seldate));
            hw.setId(id);
            hw.setTitle(title);
            hw.setType(type);
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(details);
            if (hs.addHomework(hw) && hs.addDetail(id, arrayList)) {
                request.setAttribute("rs", "布置成功");
            } else {
                request.setAttribute("rs", "布置失败");

            }


        } catch (ParseException e) {
            e.printStackTrace();
        }



        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getRequestDispatcher("index5.jsp").forward(request, response);
//        response.sendRedirect("index5.jsp");

    }
}
