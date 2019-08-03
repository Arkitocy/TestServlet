import Bean.Homework;
import Service.HomeworkService;

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

@WebServlet(name = "HomeWorkSelectUploadServlet")
public class HomeWorkSelectUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HomeworkService hs = new HomeworkService();
        String seldate = request.getParameter("seldate");
        String type = request.getParameter("type");
        try {
            ArrayList<Homework> arrayList = hs.getHomeworkByDateAndType(sdf.parse(seldate), type);
            HttpSession se1 = request.getSession();
            se1.setAttribute("homelist", arrayList);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("index4.jsp");
    }
}

