package com.bkap.btl_jsp.controller.backend.classs;

import com.bkap.btl_jsp.dao.ClassDao;
import com.bkap.btl_jsp.entities.Class;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "update-class", value = "/update-class")
public class UpdateClass extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("menu", "Class");

        ClassDao classDao = ClassDao.getInstance();

        request.setAttribute("item", classDao.findById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("views/pages/class/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("menu", "Class");

        ClassDao classDao = ClassDao.getInstance();
        Class aClass = new Class();

        aClass.setId(Integer.parseInt(request.getParameter("id")));
        aClass.setName(request.getParameter("name"));
        aClass.setStatus(Integer.parseInt(request.getParameter("status")));

        if (classDao.update(aClass)) {
            System.out.println("Update Thanh Cong");
        }
        else {
            System.out.println("Update that bai");
        }
        response.sendRedirect("index-class");
    }
}
