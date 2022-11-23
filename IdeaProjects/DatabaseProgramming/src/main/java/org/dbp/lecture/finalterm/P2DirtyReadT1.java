package org.dbp.lecture.finalterm;

public class P2DirtyReadT1 {

    public static void main(String[] args) throws SQLException {
        Connector conn = new Connector("root", "1234", "db");
        Connection connection = conn.getConnection();
        Statement stmt = conn.getStmt();

        stmt.executeUpdate("CREATE OR REPLACE TABLE users (id INTEGER, name VARCHAR(50), age INTEGER); ");
        stmt.executeUpdate("INSERT INTO users VALUES (1,'Joe',20), (2, 'Jill', 25);");

        connection.setAutoCommit(false);
        //        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        String sql = "SELECT age FROM users WHERE id = 1;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT age FROM users WHERE id = 1;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

        sql = "SELECT age FROM users WHERE id = 1;";
        DataRetriever.showResultSet(sql, stmt.executeQuery(sql));

    }

}

