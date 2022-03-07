package com.bkap.btl_jsp.controller.backend.subject;

import com.bkap.btl_jsp.constants.JSPConstant;
import com.bkap.btl_jsp.dao.SubjectDao;
import com.bkap.btl_jsp.entities.Subject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "index-subject", value = "/index-subject")
public class IndexSubject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Subject subject = new Subject();
        SubjectDao subjectDao = SubjectDao.getInstance();

        request.setAttribute("listSub", subjectDao.getAll());
        request.setAttribute("listSem", JSPConstant.listSem);

        request.setAttribute("menu", "Subject");

        request.getRequestDispatcher("views/pages/subject/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("menu", "Subject");

        Subject subject = new Subject();
        SubjectDao subjectDao = SubjectDao.getInstance();

        subject.setName(request.getParameter("name"));
        subject.setMaxScore(Integer.parseInt(request.getParameter("maxScore")));
        subject.setSession(request.getParameter("session"));
        subject.setStatus(Integer.parseInt(request.getParameter("status")));

        if (subjectDao.add(subject)) {
            System.out.println("Thanh cong");
            response.sendRedirect("index-subject");
        }
        else {
            System.out.println("That bai");
        }

    }
}
