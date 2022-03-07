package com.bkap.btl_jsp.utils;

import com.bkap.btl_jsp.constants.JSPConstant;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyJDBCConnect {

    public static Connection getConnect() {

        try {
            Class.forName(JSPConstant.DRIVE);
            Connection sqlConn = DriverManager.getConnection(JSPConstant.URL, JSPConstant.USER, JSPConstant.PASS);
            return sqlConn;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
