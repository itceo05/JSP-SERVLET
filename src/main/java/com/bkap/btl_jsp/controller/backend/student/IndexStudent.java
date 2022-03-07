package com.bkap.btl_jsp.controller.backend.student;

import com.bkap.btl_jsp.dao.ClassDao;
import com.bkap.btl_jsp.dao.StudentDao;
import com.bkap.btl_jsp.entities.Class;
import com.bkap.btl_jsp.entities.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "index-student", value = "/index-student")
public class IndexStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        StudentDao studentDao = StudentDao.getInstance();
        ClassDao classDao = ClassDao.getInstance();

        List<Class> list = classDao.getAll().stream().filter(aClass ->
                aClass.getStatus() == 1
                ).collect(Collectors.toList());
        List<Student> listStu = studentDao.getAll();
        request.setAttribute("listClass", list);
        request.setAttribute("listStu", listStu);

        request.setAttribute("menu", "Student");

        request.getRequestDispatcher("views/pages/student/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("menu", "Student");

        StudentDao studentDao = StudentDao.getInstance();
        ClassDao classDao = ClassDao.getInstance();
        Student stu = new Student();

        System.out.println("Name : "+ request.getParameter("name"));
        stu.setId(request.getParameter("id"));
        stu.setName(request.getParameter("name"));
        stu.setSex(Integer.parseInt(request.getParameter("sex")));
        stu.setBirthday(request.getParameter("birthday"));
        stu.setClassId(Integer.parseInt(request.getParameter("classId")));
        stu.setStatus(Integer.parseInt(request.getParameter("status")));

        if (studentDao.add(stu)) {
            System.out.println("Thanh Cong");
        }
        else {
            System.out.println("That Bai");
        }

        response.sendRedirect("index-student");
    }
}
