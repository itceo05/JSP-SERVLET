package com.bkap.btl_jsp.controller.fontend.acount;

import com.bkap.btl_jsp.dao.AccountDao;
import com.bkap.btl_jsp.entities.Admin;
import com.bkap.btl_jsp.utils.DataEncode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "register", value = "/register")
public class RegisterJsp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("views/pages/acount/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        AccountDao accountDao = AccountDao.getInstance();



        Admin admin = new Admin(
                0,
                request.getParameter("user"),
                request.getParameter("email"),
                DataEncode.getMd5(request.getParameter("password")),
                ""
        );

        if(accountDao.add(admin)) {
            session.setAttribute("infoUser", admin);
        }
        response.sendRedirect("login");
    }
}
