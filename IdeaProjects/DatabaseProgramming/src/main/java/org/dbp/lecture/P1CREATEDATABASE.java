package org.dbp.lecture;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class P1CREATEDATABASE {
    public static void main(String[] args) {
        String id = "root";
        String pw = "tmdgns12";
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        } catch (SQLException e) {
            System.out.println("fail to connect");
        }

        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("fail to make stmt");
        }

        try {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS dbp;");
            SQLWarning warning = stmt.getWarnings();
            System.out.println(warning);
        } catch (SQLException e){
            System.out.println("이미 존재하는 데이터베이스입니다.");
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("fail to close connection");
        }

        System.out.println("i'm alive");
    }
}
