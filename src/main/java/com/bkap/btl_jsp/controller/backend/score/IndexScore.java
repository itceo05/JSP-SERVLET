package com.bkap.btl_jsp.controller.backend.score;

import com.bkap.btl_jsp.constants.JSPConstant;
import com.bkap.btl_jsp.dao.ClassDao;
import com.bkap.btl_jsp.dao.StudentDao;
import com.bkap.btl_jsp.dao.SubScoreDao;
import com.bkap.btl_jsp.dao.SubjectDao;
import com.bkap.btl_jsp.entities.Student;
import com.bkap.btl_jsp.entities.SubjectCore;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "index-score", value = "/index-score")
public class IndexScore extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassDao classDao = ClassDao.getInstance();

        request.setAttribute("listClass", classDao.getAll());
        request.setAttribute("listSem", JSPConstant.listSem);

        request.setAttribute("menu", "Point");

        request.getRequestDispatcher("views/pages/score/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("menu", "Point");

        ClassDao classDao = ClassDao.getInstance();
        SubjectDao subjectDao = SubjectDao.getInstance();
        StudentDao studentDao = StudentDao.getInstance();
        SubScoreDao subScoreDao = SubScoreDao.getInstance();

        request.setAttribute("listClass", classDao.getAll());
        request.setAttribute("listSem", JSPConstant.listSem);

        request.setAttribute("oldClass", request.getParameter("class"));
        request.setAttribute("oldSem", request.getParameter("sem"));

        request.setAttribute("listSub",
                subjectDao.getAll().stream().filter(
                        x -> x.getSession().equals(request.getAttribute("oldSem"))
                ).collect(Collectors.toList())
        );

        if (request.getParameter("subId") != null) {
            int subId = Integer.parseInt(request.getParameter("subId"));
            int type = Integer.parseInt(request.getParameter("type"));
            int classId = Integer.parseInt(request.getParameter("class"));

            request.setAttribute("oldSub", subId);
            request.setAttribute("oldType", type);

            List<SubjectCore> listSubScore = subScoreDao.findByCondition(
                    classId,
                    subId,
                    type
            );

            List<Student> listStu = studentDao.findByClass(classId);

            request.setAttribute("listStu", listSubScore.isEmpty() ? listStu : listSubScore);
            request.setAttribute("oldS", listSubScore.isEmpty() ? false : true);
            if (request.getParameterValues("exams[]") != null) {

                String[] listExams = request.getParameterValues("exams[]");
                String[] listPoint = request.getParameterValues("points[]");
                String[] listStuId = request.getParameterValues("stuId[]");

                for (int i = 0; i < listExams.length; i++) {

                    if (!listSubScore.isEmpty()) {
                        subScoreDao.remote(subId, listStuId[i]);
                    }

                    SubjectCore subjectCore = new SubjectCore(
                            listStuId[i],
                            subId,
                            type,
                            Integer.parseInt(listExams[i]),
                            Float.parseFloat(listPoint[i]),
                            "",
                            0
                    );
                    subScoreDao.add(subjectCore);
                }

                response.sendRedirect("index-score");
                return;
            }
        }
        request.getRequestDispatcher("views/pages/score/index.jsp").forward(request, response);
    }
}
