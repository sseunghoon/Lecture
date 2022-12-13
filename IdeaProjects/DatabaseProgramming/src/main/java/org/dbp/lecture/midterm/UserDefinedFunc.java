package org.dbp.lecture.midterm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDefinedFunc {

    public static void main(String[] args) throws Exception{
        String id = "root";
        String pw = "1234";
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        Statement stmt = conn.createStatement();

        stmt.executeQuery("use dbp");
        System.out.println("Connecting to...:" + conn.getCatalog());
        stmt.executeUpdate("CREATE OR REPLACE FUNCTION SUM_VALUES() RETURNS INT " +
                "BEGIN DECLARE sum INT;" +
                "SET sum = 0;" +
                "FOR i IN (SELECT x FROM ten)" +
                "DO " +
                "SET sum = sum + i.x;" +
                "END FOR;" +
                "RETURN sum;" +
                "END");
        ResultSet rs = stmt.executeQuery("SELECT SUM_VALUES();");
        rs.next();
        System.out.println(rs.getInt(1));
    }
}
