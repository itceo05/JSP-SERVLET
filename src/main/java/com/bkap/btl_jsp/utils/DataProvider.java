package com.bkap.btl_jsp.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DataProvider {

    private static ResultSet resultSet;
    private static Connection sqlConn = MyJDBCConnect.getConnect();
    private static CallableStatement cs;

    public static ResultSet excuteQuery(String sql, Object... params) {
        try {
            cs = sqlConn.prepareCall(sql);
            if (params != null)
                for (int i = 0; i < params.length; i++) {
                    cs.setObject(i + 1, params[i]);
                }

            resultSet = cs.executeQuery();
            return resultSet;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int excuteUpdate(String sql, Object... params) {
        try {
            cs = sqlConn.prepareCall(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    cs.setObject(i + 1, params[i]);
                }
            }

            return cs.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
