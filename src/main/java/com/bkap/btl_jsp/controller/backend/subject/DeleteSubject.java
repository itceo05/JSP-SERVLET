package com.bkap.btl_jsp.controller.backend.subject;

import com.bkap.btl_jsp.dao.ClassDao;
import com.bkap.btl_jsp.dao.SubjectDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete-subject", value = "/delete-subject")
public class DeleteSubject extends HttpServlet {
    public SubjectDao subjectDao = SubjectDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (subjectDao.remote(Integer.parseInt(request.getParameter("id")))) {
            System.out.println("Thanh cong");
            response.sendRedirect("index-subject");
        }
        else {
            System.out.println("That bai");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
