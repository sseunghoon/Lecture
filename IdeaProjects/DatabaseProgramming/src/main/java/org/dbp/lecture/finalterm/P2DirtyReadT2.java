package org.dbp.lecture.finalterm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class P2DirtyReadT2 {

    public static void main(String[] args) throws SQLException {
        Connector conn = new Connector("root", "1234", "db");
        Connection connection = conn.getConnection();
        Statement stmt = conn.getStmt();

        connection.setAutoCommit(false);

        String sql = "UPDATE users SET age = 21 WHERE id = 1;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        connection.rollback();
    }

}

