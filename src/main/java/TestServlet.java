import Bean.Product;
import Service.ProductService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class StudentServlet
 */
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //取出请求中的参数
//        String productname = request.getParameter("pname");
        ProductService ts = new ProductService();
//        ToyService ts = new ToyService();
        ArrayList<Product> ls = ts.showAll();
        String rs = JSONObject.toJSONString(ls, SerializerFeature.DisableCircularReferenceDetect);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().append(rs);


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}