package org.dbp.lecture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

class Loan {
    private String  loanNumber;
    private String  branchName;
    private int     amount;

    public Loan(String loanNumber, String branchName, int amount) {
        this.loanNumber = loanNumber;
        this.branchName = branchName;
        this.amount = amount;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

public class LoanLoader {
    public static void main(String[] args) throws Exception{
        String id = "root";
        String pw = "tmdgns12";
        BufferedReader r = new BufferedReader(new FileReader("/Users/songseunghun/Desktop/DBP/Data/loan.txt"));
        ArrayList<Loan> list = new ArrayList<>();
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        Statement stmt = conn.createStatement();

        stmt.executeQuery("CREATE DATABASE IF NOT EXISTS dbp;");
        stmt.executeQuery("USE dbp");
        stmt.executeQuery("CREATE TABLE IF NOT EXISTS student(" +
                "name VARCHAR(50)," +
                "id VARCHAR(50)," +
                "point INT);");
        ResultSet rs = stmt.executeQuery("DESCRIBE student;");
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i < rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i) + "(" + rsmd.getColumnLabel(i) + ") ");
        }
        System.out.println();
        while(rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount() ; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }


//        stmt.executeUpdate("USE assignment1;");
//        ResultSet rs = stmt.executeQuery("SELECT * FROM charger;");
//        ResultSetMetaData rsmd = rs.getMetaData();
//        System.out.println(rsmd.getColumnName(1) + ":" + rsmd.getColumnTypeName(1));
//        while(rs.next()) {
//            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                System.out.println(rs.getString(i));
//            }
//        }

//
//        r.lines().forEach(str -> {
//            String arr[] = str.split(",");
//            list.add(new Loan(arr[0], arr[1], Integer.parseInt(arr[2])));
//        });
//
//        stmt.executeUpdate("CREATE OR REPLACE DATABASE midterm;");
//        stmt.executeUpdate("USE midterm;");
//        stmt.executeUpdate(
//                "CREATE TABLE loan (loan_number VARCHAR(50), " +
//                        "branch_name VARCHAR(50), amount INT);");
//
//        for (Loan loan: list) {
//            String insertInto = "INSERT INTO loan VALUES ('" + loan.getLoanNumber() + "','" + loan.getBranchName() + "'," + loan.getAmount() + ");";
//            System.out.println(insertInto);
//            stmt.executeUpdate(insertInto);
//        }

        r.close();
        conn.close();
    }
}
