package org.dbp.lecture.midterm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;


public class JSONarrJsonObj {

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
        System.out.println("===================================================");
        for(Object obj: arr) {
            System.out.println(obj);
        }
        System.out.println("===================================================");
        for(int i = 0; i < arr.length(); i++) {
            Object value = arr.get(i);
            System.out.println(value);
        }
        System.out.println("===================================================");
        for(int i = 0; i < arr.length(); i++) {
            JSONObject value = (JSONObject) arr.get(i);
            System.out.println(value);
        }
        System.out.println("===================================================");
        for(int i = 0; i < arr.length(); i++) {
            Object value = arr.get(i);
            if (value instanceof  JSONObject) {
                JSONObject jsonObject = (JSONObject) value;
            }
            System.out.println(value);
        }
        System.out.println("===================================================!");
        for(int i = 0; i < arr.length(); i++) {
            JSONObject value = arr.getJSONObject(i);

            String accountNumber = value.getString("account_number");
            String customerName = value.getString("customer_name");
            System.out.println(accountNumber);
            System.out.println(customerName);
        }
        System.out.println("===================================================?");
        for(int i = 0; i < arr.length(); i++) {
            JSONObject value = arr.getJSONObject(i);

            for(String key: value.keySet()) {
                System.out.println(key + " ->" + value.get(key));
            }
        }
    }

}

