package web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post方法");
        String name = req.getParameter("name");
        String passwd = req.getParameter("passwd");

        if ("2766132847".equals(name) && "123456".equals(passwd)){
            System.out.println("登录成功");
            Cookie nameCookie = new Cookie("name",name);
            nameCookie.setMaxAge(3000);   // 设置为3000秒
            resp.addCookie(nameCookie);
        }else{
            System.out.println("登录失败");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get方法");
    }
}
