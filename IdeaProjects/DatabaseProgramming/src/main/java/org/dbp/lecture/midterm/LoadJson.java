package org.dbp.lecture.midterm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadJson {

    public static void main(String[] args) throws Exception{
        BufferedReader r = new BufferedReader(new FileReader("/Users/songseunghun/Desktop/DBP/Data/depositor.json"));
        String contents = r.lines().reduce("", (e1, e2) -> e1 + e2);
        r.close();
        System.out.println(contents);

        JSONArray arr = new JSONArray(contents);
        for(Object o : arr) {
            JSONObject jo = (JSONObject) o;
            System.out.println(jo.getString("account_number") + " is " + jo.getString("customer_name"));
        }

    }
}
