package org.dbp.lecture;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

public class DOMPractice {
    public static void main(String[] args) throws Exception{
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement("account");
        doc.appendChild(root);
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        System.out.println(doc);
    }
}
