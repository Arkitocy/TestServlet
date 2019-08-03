import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import Service.HomeworkService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "HomeWorkUploadServlet")
public class HomeWorkUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public HomeWorkUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //当内存中文件数据达到THRESHOLD_SIZE后，就写入临时文件中，避免上传大文件时，消化太多内存
        HomeworkService hs = new HomeworkService();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        upload.setSizeMax(2*1024*1024);
        //  String savePath=request.getServletContext().getRealPath("bsea"+File.separator+"img"+File.separator);
        String savePath="D:/tmp/";
        System.out.println("--f1- request.getContextPath()-----"+ request.getContextPath());
        System.out.println("--f2- request.getContextPath()-----"+ request.getServletContext().getContextPath());
        System.out.println("--f3- request.getContextPath()-----"+ request.getServletContext().getRealPath("file"));
        System.out.println("--f4- request.getContextPath()-----"+ request.getSession().getServletContext().getRealPath("file"));
        System.out.println("--f5- request.getContextPath()-----"+ savePath);
        String uid="";
        String hid="";
        List items;
        try {
            items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while(iter.hasNext()){
                FileItem item = (FileItem) iter.next();
                //isFormField().  如果循环里，本次循环的元素的类型是file，那么这个方法返回false, 如果不是文件类型，就返回true
                if (item.isFormField()) {//普通表单域
                    System.out.println("---isFormField=true------"+item.getFieldName()+"----------------"+item.getString());
                    if("uid".equals(item.getFieldName())){
                        uid=item.getString();
                    }
                    if("hid".equals(item.getFieldName())){
                        hid=item.getString();
                    }
                } else {
                    String fileName = item.getName().replace("/", "\\");
                    System.out.println("---fileName------"+fileName+"----------------");
                    int i = fileName.lastIndexOf("\\");
                    fileName = fileName.substring(i+1);
                    //避免重复
                    fileName = System.currentTimeMillis() + fileName;
                    //File.separator 在window系统下，表示 \  ，在linux系统下是一个/
                    File uploadedFile = new File( savePath+ fileName);
                    item.write(uploadedFile);


                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        hs.addHomework_User(hid,uid);
        response.sendRedirect("index4.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
