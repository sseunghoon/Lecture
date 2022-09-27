package org.dbp.lecture;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

class Account {
    String  accountNumber;
    String  branchName;
    int     balance;

    public Account(String _accountNumber, String _branchName, int _balance) {
        this.accountNumber = _accountNumber;
        this.branchName = _branchName;
        this.balance = _balance;
    }
}

public class XMLLoading {
    public static void main(String[] args) throws Exception{
        BufferedReader r = new BufferedReader(new FileReader("/Users/songseunghun/Desktop/DBP/Data/account.xml"));
        String data = r.lines().reduce("", (e1, e2)-> e1 + e2);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(data)));

        String id = "root";
        String pw = "tmdgns12";
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE OR REPLACE DATABASE midterm");
        stmt.executeUpdate("USE midterm");
        stmt.executeUpdate("CREATE TABLE account_number VARCHAR(50), " + "branch_name VARCHAR(50), balance INT");

        NodeList nodeList = doc.getElementsByTagName("account");
        ArrayList<Account> list = new ArrayList<Account>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element account = (Element) nodeList.item(i);
            String accountNumber = account.getElementsByTagName("account_number").item(0).getTextContent();
            String branchName = account.getElementsByTagName("branch_name").item(0).getTextContent();
            int balance = Integer.parseInt(account.getElementsByTagName("balance").item(0).getTextContent());
            list.add(new Account(accountNumber, branchName, balance));
            String insertInto = "INSERT INTO account VALUES ('"+ accountNumber + "','" + branchName + balance + ")";
            stmt.executeUpdate(insertInto);
            System.out.println(insertInto);
        }
        list.forEach(e -> {
            System.out.println(e.accountNumber +", "+ e.branchName +", "+ e.balance);
        });
        System.out.println(doc);




    }
}
