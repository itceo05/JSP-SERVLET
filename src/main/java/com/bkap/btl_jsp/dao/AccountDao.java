package com.bkap.btl_jsp.dao;

import com.bkap.btl_jsp.constants.JSPConstant;
import com.bkap.btl_jsp.entities.Admin;
import com.bkap.btl_jsp.entities.Student;
import com.bkap.btl_jsp.entities.SubjectCore;
import com.bkap.btl_jsp.utils.DataProvider;

import java.sql.ResultSet;

public class AccountDao {

    private ResultSet resultSet = null;
    private static AccountDao instance;

    private AccountDao() {

    }

    public static AccountDao getInstance() {
        if (instance == null) {
            instance = new AccountDao();
        }
        return instance;
    }

    public boolean add(Admin entity) {
        return DataProvider.excuteUpdate(JSPConstant.registerAdmin,
                entity.getName(), entity.getEmail(), entity.getPassword()) > 0;
    }

    public boolean update(Admin entity) {
        return DataProvider.excuteUpdate(JSPConstant.updateToken,
                entity.getId(), entity.getToken()) > 0;
    }

    public Admin findByToken(String token) {
        try {
            resultSet = DataProvider.excuteQuery(JSPConstant.findByToken, token);
            while (resultSet.next()) {
                Admin admin = new Admin(resultSet);
                return admin;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin findByInfo(String uEmail, String uPass) {
        try {
            resultSet = DataProvider.excuteQuery(JSPConstant.findByInfo, uEmail, uPass);
            while (resultSet.next()) {
                Admin admin = new Admin(resultSet);
                return admin;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
