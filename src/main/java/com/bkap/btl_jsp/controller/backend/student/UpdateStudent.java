package com.bkap.btl_jsp.controller.backend.student;

import com.bkap.btl_jsp.constants.JSPConstant;
import com.bkap.btl_jsp.dao.ClassDao;
import com.bkap.btl_jsp.dao.StudentDao;
import com.bkap.btl_jsp.entities.Class;
import com.bkap.btl_jsp.entities.Student;
import com.bkap.btl_jsp.utils.DataProvider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "update-student", value = "/update-student")
public class UpdateStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("menu", "Student");

        List<Class> list = ClassDao.getInstance().getAll().stream().filter(aClass ->
                aClass.getStatus() == 1
        ).collect(Collectors.toList());

        request.setAttribute("ite", StudentDao.getInstance().findById(request.getParameter("id")));
        request.setAttribute("listClass", list);
        System.out.println("Data: "+ request.getAttribute("ite"));
        request.getRequestDispatcher("views/pages/student/edit.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ResultSet resultSet = null;
        StudentDao studentDao = StudentDao.getInstance();
        Student stu = new Student();

        stu.setId(request.getParameter("ids"));
        stu.setName(request.getParameter("name"));
        stu.setSex(Integer.parseInt(request.getParameter("sex")));
        stu.setBirthday(request.getParameter("birthday"));
        stu.setClassId(Integer.parseInt(request.getParameter("classId")));
        stu.setStatus(Integer.parseInt(request.getParameter("status")));
        if (studentDao.update(stu)) {
            System.out.println("Thanh Cong");
        }
        else {
            System.out.println("That Bai");
        }

        response.sendRedirect("index-student");
    }
}
