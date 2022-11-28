package org.dbp.lecture.finalterm;

import org.mariadb.jdbc.Connection;

import java.sql.SQLException;
import java.sql.Statement;

public class P1AutoCommit {

    public static void main(String[] args) throws SQLException {
        Connector conn = new Connector("root", "1234", "db");
        Statement stmt = conn.getStmt();

        stmt.executeUpdate("CREATE OR REPLACE TABLE student (name CHAR(10), test CHAR(10), score TINYINT); ");
        stmt.executeUpdate("INSERT INTO student VALUES ('Chun', 'SQL', 75);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Chun', 'Tuning', 73);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Esben', 'SQL', 43);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Esben', 'Tuning', 31);");

        String sql = "SELECT * FROM student";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        stmt.executeUpdate("INSERT INTO student VALUES ('Kaolin', 'SQL', 56);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Kaolin', 'Tuning', 88);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Tatiana', 'SQL', 87);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Tatiana', 'Tuning', 83);");

        sql = "SELECT * FROM student";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));



        stmt.executeUpdate("CREATE OR REPLACE TABLE student (name CHAR(10), test CHAR(10), score TINYINT); ");

        conn.getConnection().setAutoCommit(false);

        conn.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        stmt.executeUpdate("INSERT INTO student VALUES ('Chun', 'SQL', 75);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Chun', 'Tuning', 73);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Esben', 'SQL', 43);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Esben', 'Tuning', 31);");

        sql = "SELECT * FROM student";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        stmt.executeUpdate("INSERT INTO student VALUES ('Kaolin', 'SQL', 56);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Kaolin', 'Tuning', 88);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Tatiana', 'SQL', 87);");
        stmt.executeUpdate("INSERT INTO student VALUES ('Tatiana', 'Tuning', 83);");

        conn.getConnection().commit();

        sql = "SELECT * FROM student";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

    }

}

