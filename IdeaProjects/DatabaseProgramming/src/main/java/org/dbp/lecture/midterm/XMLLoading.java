package org.dbp.lecture.midterm;

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

public class XMLLoading {
    public static void main(String[] args) throws Exception{
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement("account");
        doc.appendChild(root);
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        Element accountNumber = doc.createElement("accountNumber");
        accountNumber.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        accountNumber.setTextContent("A-101");
    }
}
