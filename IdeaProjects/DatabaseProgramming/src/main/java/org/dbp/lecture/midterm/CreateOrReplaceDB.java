package org.dbp.lecture.midterm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateOrReplaceDB {

    public static void main(String[] args) throws Exception{

        String id = "root";
        String pw = "1234";

        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE OR REPLACE DATABASE DBPMidTerm;");
        stmt.executeUpdate("USE DBPMidTerm;");
        stmt.executeUpdate("CREATE OR REPLACE TABLE test (id int NOT NULL UNIQUE, name varchar(50));");
        stmt.executeUpdate("INSERT INTO test VALUES (1, 'abc');");
        stmt.executeUpdate("INSERT INTO test VALUES (2, 'def');");
        stmt.executeUpdate("INSERT INTO test VALUES (3, 'ghi');");

        ResultSet rs = stmt.executeQuery("SELECT * FROM test WHERE name='abc';");

        while (rs.next()) {
            System.out.println(rs.getString(2) + " " + rs.getInt(1));
        }


        connection.close();
    }
}
