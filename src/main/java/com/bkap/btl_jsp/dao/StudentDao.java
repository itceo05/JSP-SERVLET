package com.bkap.btl_jsp.dao;

import com.bkap.btl_jsp.constants.JSPConstant;
import com.bkap.btl_jsp.dao.base.IDao;
import com.bkap.btl_jsp.entities.Class;
import com.bkap.btl_jsp.entities.Student;
import com.bkap.btl_jsp.utils.DataProvider;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IDao<String, Student> {

    ResultSet resultSet = null;
    private static StudentDao instance;

    private StudentDao() {

    }

    public static StudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDao();
        }

        return instance;
    }

    public List<Student> findByClass(int id) {
        List<Student> listStu = new ArrayList<>();
        try {
            resultSet = DataProvider.excuteQuery(JSPConstant.studentFindClass, id);
            while (resultSet.next()) {
                Student newStu = new Student(resultSet);
                listStu.add(newStu);
            }
            return listStu;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        try {
            resultSet = DataProvider.excuteQuery(JSPConstant.getAllStudent);
            while (resultSet.next()) {
                Student newStu = new Student(resultSet);
                list.add(newStu);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Student findById(String id) {
        try {
            resultSet = DataProvider.excuteQuery(JSPConstant.studentFindById, id);
            while (resultSet.next()) {
                Student newStu = new Student(resultSet);
                return newStu;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Student entity) {
        try {
            return DataProvider.excuteUpdate(JSPConstant.addStudent, entity.getId(), entity.getName(), entity.getSex(), entity.getClassId(), entity.getBirthday(), entity.getStatus()) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Student entity) {
        try {
            return DataProvider.excuteUpdate(JSPConstant.updateStudent, entity.getId(), entity.getName(), entity.getSex(), entity.getClassId(), entity.getBirthday(), entity.getStatus()) > 0;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remote(String id) {
        try {
            return DataProvider.excuteUpdate(JSPConstant.remoteStudent, id) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
