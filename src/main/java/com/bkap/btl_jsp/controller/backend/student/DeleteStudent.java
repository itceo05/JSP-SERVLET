package com.bkap.btl_jsp.controller.backend.student;

import com.bkap.btl_jsp.dao.ClassDao;
import com.bkap.btl_jsp.dao.StudentDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete-student", value = "/delete-student")
public class DeleteStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StudentDao.getInstance().remote(request.getParameter("id"))) {
            System.out.println("Thanh Cong");
        }
        else {
            System.out.println("That Bai");
        }
        response.sendRedirect("index-student");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
