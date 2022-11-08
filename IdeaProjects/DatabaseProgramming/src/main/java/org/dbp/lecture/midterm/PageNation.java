package org.dbp.lecture.midterm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PageNation {

    public static void main(String[] args) throws Exception{

        String id = "root";
        String pw = "1234";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("USE DBP;");
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM ten;");
        rs.next();
        int maxPage = rs.getInt(1) / 5 + ((rs.getInt(1) % 5 == 0)? 0:1) ;

        for (int i = 0; i < maxPage; i++) {
            rs = stmt.executeQuery("SELECT * from ten ORDER BY value LIMIT " + i*5 + ",5;");
            System.out.println("Page" + (i+1));
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        }
    }
}
