package com.bkap.btl_jsp.controller.backend.dashboard;

import com.bkap.btl_jsp.dao.AccountDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "admin", value = "/admin")
public class DashboardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("menu", "Dashboard");

        HttpSession session = request.getSession();

        if (session.getAttribute("access_token") == null) {
            response.sendRedirect("login");
            return;
        }

        AccountDao accountDao = AccountDao.getInstance();
        String access_token = (String) session.getAttribute("access_token");
        request.setAttribute("uName", accountDao.findByToken(access_token).getName());
        request.getRequestDispatcher("views/pages/base/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
