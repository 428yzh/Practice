package web;

import beans.User;
import daoImpl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String userName = req.getParameter("userName");
        String passwd = req.getParameter("passwd");
        User user = new User(userName,passwd);
        int res = userDaoImpl.addOne(user);
        System.out.println("post方法" + res);
    }
}
