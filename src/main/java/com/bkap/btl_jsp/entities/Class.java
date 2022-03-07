package com.bkap.btl_jsp.entities;

import lombok.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Class {
    private int id;
    private String name;
    private int status;
    private String createAt;
    private int totalStu;

    public Class(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("className");
        this.status = resultSet.getInt("status");
        this.totalStu = resultSet.getInt("totalStu");
        this.createAt = resultSet.getString("createAt");
    }
}
