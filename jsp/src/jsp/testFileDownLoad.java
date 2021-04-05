package jsp;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class testFileDownLoad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = "1.jpeg";
        ServletContext context = getServletContext();
        InputStream inputStream = context.getResourceAsStream("/public/" + fileName);
        OutputStream outputStream = resp.getOutputStream();
        // 获取类型，再告诉客户端的响应头是啥类型
        String miniType = context.getMimeType("/public/" + fileName);

        resp.setContentType(miniType);
        // 回传给客户端
        IOUtils.copy(inputStream,outputStream);
        // 告诉客户端是下载的
        resp.setHeader("Content-Disposition","attachment;filename=" + fileName);
        System.out.println("get访问");
    }
}
