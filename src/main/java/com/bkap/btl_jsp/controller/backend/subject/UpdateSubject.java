package com.bkap.btl_jsp.controller.backend.subject;

import com.bkap.btl_jsp.constants.JSPConstant;
import com.bkap.btl_jsp.dao.SubjectDao;
import com.bkap.btl_jsp.entities.Subject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "update-subject", value = "/update-subject")
public class UpdateSubject extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("menu", "Subject");

        SubjectDao subjectDao = SubjectDao.getInstance();

        request.setAttribute("sub", subjectDao.findById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("listSem", JSPConstant.listSem);
        request.getRequestDispatcher("views/pages/subject/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("menu", "Subject");

        SubjectDao subjectDao = SubjectDao.getInstance();

        Subject subject = new Subject(
          Integer.parseInt(request.getParameter("id")),
          request.getParameter("name"),
          request.getParameter("session"),
          Integer.parseInt(request.getParameter("status")),
          Integer.parseInt(request.getParameter("maxScore"))
        );

        if (subjectDao.update(subject)) {
            System.out.println("Thanh cong");
            response.sendRedirect("index-subject");
        }
        else {
            System.out.println("That bai");
        }
    }
}
