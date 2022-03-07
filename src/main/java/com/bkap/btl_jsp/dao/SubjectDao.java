package com.bkap.btl_jsp.dao;

import com.bkap.btl_jsp.constants.JSPConstant;
import com.bkap.btl_jsp.dao.base.IDao;
import com.bkap.btl_jsp.entities.Subject;
import com.bkap.btl_jsp.utils.DataProvider;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao implements IDao<Integer, Subject> {
    private ResultSet resultSet = null;
    private static SubjectDao instance;

    private SubjectDao() {

    }

    public static SubjectDao getInstance() {
        if (instance == null) {
            instance = new SubjectDao();
        }
        return instance;
    }

    @Override
    public List<Subject> getAll() {
        List<Subject> list = new ArrayList<>();
        try {
            resultSet = DataProvider.excuteQuery(JSPConstant.getAllSubject);
            while (resultSet.next()) {
                Subject subject = new Subject(resultSet);
                list.add(subject);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Subject findById(Integer id) {
        try{
            resultSet = DataProvider.excuteQuery(JSPConstant.subjectFindById, id);
            resultSet.next();
                Subject subject = new Subject(resultSet);
                return subject;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Subject entity) {
        return DataProvider.excuteUpdate(JSPConstant.addSubject, entity.getName(), entity.getMaxScore(), entity.getSession(), entity.getStatus()) > 0;
    }

    @Override
    public boolean update(Subject entity) {
        return DataProvider.excuteUpdate(JSPConstant.updateSubject, entity.getId(), entity.getName(), entity.getMaxScore(), entity.getSession(), entity.getStatus()) > 0;
    }

    @Override
    public boolean remote(Integer id) {
        return DataProvider.excuteUpdate(JSPConstant.remoteSubject, id) > 0;
    }
}
