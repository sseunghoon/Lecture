package org.dbp.lecture.midterm;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadPureText {

    public static void main(String[] args) throws Exception{
        BufferedReader r = new BufferedReader(new FileReader("/Users/songseunghun/Desktop/DBP/Data/loan.txt"));

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
