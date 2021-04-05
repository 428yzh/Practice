package web;

import bean.person;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ajaxServlet extends BaseServlet {
    protected void jqueryAjaxGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html,charset=utf-8");
        person a = new person(1 , "xzx");
        Gson gson = new Gson();
        String data = gson.toJson(a);
        resp.getWriter().write(data);
    }
    protected void jqueryAjaxPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html,charset=utf-8");
        person a = new person(2 , "yzh");
        Gson gson = new Gson();
        String data = gson.toJson(a);
        resp.getWriter().write(data);
    }
    protected void jqueryAjaxSerialize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("value");
        person a = new person(2 , name);
        System.out.println(a);
        Gson gson = new Gson();
        String data = gson.toJson(a);
        resp.getWriter().write(data);
        System.out.println(1);
    }
}
