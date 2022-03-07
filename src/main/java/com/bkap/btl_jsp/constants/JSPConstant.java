package com.bkap.btl_jsp.constants;

import java.util.HashMap;
import java.util.Map;

public class JSPConstant {
    public static String[] listSem = {"SEM I", "SEM II", "SEM III", "SEM IV"};
//    public static Map<String, String> semester = new HashMap<>();

    //    Config connect DB
    public static final String DRIVE = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BTL_JSP";
    public static final String USER = "sa";
    public static final String PASS = "12345";

// Config call Procedure
    public static final String getAllClass = "{ call sp_Class_GetAll() }";
    public static final String addClass = "{ call sp_Class_Add(?, ?) }";
    public static final String updateClass = "{ call sp_Class_Update(?, ?, ?) }";
    public static final String remoteClass = "{ call sp_Class_Remove(?) }";
    public static final String classFindById = "{ call sp_Class_FindId(?) }";

    public static final String getAllStudent = "{ call sp_Student_GetAll() }";
    public static final String addStudent = "{ call sp_Student_Add(?, ?, ?, ?, ?, ?) }";
    public static final String updateStudent = "{ call sp_Student_Update(?, ?, ?, ?, ?, ?) }";
    public static final String remoteStudent = "{ call sp_Student_Remove(?) }";
    public static final String studentFindById = "{ call sp_Student_FindId(?) }";
    public static final String studentFindClass = "{ call sp_Student_FindClass(?) }";

    public static final String getAllSubject = "{ call sp_Subject_GetAll() }";
    public static final String addSubject = "{ call sp_Subject_Add(?, ?, ?, ?) }";
    public static final String updateSubject = "{ call sp_Subject_Update(?, ?, ?, ?, ?) }";
    public static final String remoteSubject = "{ call sp_Subject_Remove(?) }";
    public static final String subjectFindById = "{ call sp_Subject_FindId(?) }";

    public static final String addSubScore = "{ call sp_SubScore_Add(?, ?, ?, ?, ?) }";
    public static final String subScoreFind = "{ call sp_SubScore_Find(?, ?, ?) }";
    public static final String subScoreRemove = "{ call sp_SubScore_Remove(?, ?) }";

    public static final String registerAdmin = "{ call sp_Admin_Add(?, ?, ?) }";
    public static final String updateToken = "{ call sp_Admin_Update(?, ?) }";
    public static final String findByToken = "{ call sp_Admin_FindByToken(?) }";
    public static final String findByInfo = "{ call sp_Admin_FindInfo(?, ?) }";

    public static final String searchScore = "{ call sp_Search_Score(?) }";
    // Message Notifi
//    public static final String
}
