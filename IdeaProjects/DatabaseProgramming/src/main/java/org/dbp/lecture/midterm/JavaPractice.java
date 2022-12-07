package org.dbp.lecture;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class JavaPractice {

    public static void main(String[] args) throws Exception{
        BufferedReader r = new BufferedReader(new FileReader("/Users/songseunghun/Desktop/DBP/Data/depositor.json"));

        String tmp = null;
        String contents = "";

//        String lines = r.lines().reduce("", (e1, e2)-> e1 +"\n"+ e2);
//        System.out.println(lines);

        tmp = r.readLine();
        while (tmp != null){
            contents = contents + tmp + "\n";
            tmp = r.readLine();
        }
        System.out.println(contents);
    }
}