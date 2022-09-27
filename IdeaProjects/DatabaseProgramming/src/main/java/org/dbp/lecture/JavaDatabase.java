package org.dbp.lecture;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class JavaDatabase {

    public static void main(String[] args) throws Exception{

        String str = "";
        String lines = "";
        BufferedReader r = new BufferedReader(new FileReader("/Users/songseunghun/Desktop/DBP/Data/depositor.json"));
//        str = r.readLine();
//        lines += str;
//        while (str != null) {
//            str = r.readLine();
//            lines += str;
//        }
        List<String> list = r.lines().toList();
        for(String value: list) {
            lines += value;
        }
        r.close();
        JSONArray arr = new JSONArray(lines);
        System.out.println(arr);
    }

}

