package com.bkap.btl_jsp.dao;

import com.bkap.btl_jsp.constants.JSPConstant;
import com.bkap.btl_jsp.dao.base.IDao;
import com.bkap.btl_jsp.entities.Class;
import com.bkap.btl_jsp.utils.DataProvider;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassDao implements IDao<Integer, Class> {

    ResultSet resultSet = null;
    private static ClassDao instance;

    private ClassDao() {

    }

    public static ClassDao getInstance() {
        if (instance == null) {
            instance = new ClassDao();
        }

        return instance;
    }

    @Override
    public List<Class> getAll() {
        List<Class> list = new ArrayList<>();
        try {
            resultSet = DataProvider.excuteQuery(JSPConstant.getAllClass);
            while (resultSet.next()) {
                Class newClass = new Class(resultSet);
                list.add(newClass);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Class findById(Integer id) {
        try {
            resultSet = DataProvider.excuteQuery(JSPConstant.classFindById, id);
            while (resultSet.next()) {
                Class newClass = new Class(resultSet);
                return newClass;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Class entity) {
        try {
            return DataProvider.excuteUpdate(JSPConstant.addClass, entity.getName(), entity.getStatus()) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Class entity) {
        try {
            return DataProvider.excuteUpdate(JSPConstant.updateClass, entity.getId(), entity.getName(), entity.getStatus()) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remote(Integer id) {
        try {
            return DataProvider.excuteUpdate(JSPConstant.remoteClass, id) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}