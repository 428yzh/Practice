package jsp;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class testFile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // file加工工厂 (这些工厂，和工厂模式有什么区别呢）
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        // 创造一个jar包的工具对象
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
        // 解析上传的数据
        try{
            List<FileItem> list = fileUpload.parseRequest(req);   // 解析上传的数据
            for (FileItem fileItem : list){
                if (fileItem.isFormField()){
                    String fileInputName = fileItem.getFieldName();
                    String fileValue = fileItem.getString();
                }else{
                    String fileInputName = fileItem.getFieldName();    // 获取input中的name
                    String fileName = fileItem.getName();   // 获取文件的名
                    fileItem.write(new File("D://" + fileName));    // 这里一步是上传到哪去？
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // 现在让他转到一个页面上去
        req.getRequestDispatcher("/upLoad_success.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get访问");
    }
}
