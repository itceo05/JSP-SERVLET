package com.bkap.btl_jsp.controller.backend.classs;

import com.bkap.btl_jsp.dao.ClassDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete-class", value = "/delete-class")
public class DeleteClass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ClassDao.getInstance().remote(Integer.parseInt(request.getParameter("id")))) {
            System.out.println("Thanh Cong");
        }
        else {
            System.out.println("That Bai");
        }

        response.sendRedirect("index-class");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
