import Bean.HomeWorkRank;
import Service.HomeworkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RankServlet")
public class RankServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomeworkService hs = new HomeworkService();
        String type = request.getParameter("type");
        ArrayList<HomeWorkRank> ahr = hs.getRank(type);
        HttpSession se1 = request.getSession();
        se1.setAttribute("ranklist", ahr);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("rank.jsp");
    }

}
