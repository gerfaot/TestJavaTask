package com.example.demo.service;


import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceTable {
    ArrayList<String> arr = new ArrayList();
    JSONArray ar = new JSONArray();
    JSONObject obj;

    public static Statement statmt;
    public static ResultSet resSet;

    public ArrayList<String> testFun(String first, String second) {
        connect(first, second);
        return (arr);
    }

    public ArrayList<String> connect(String first, String second) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/Users/Leonid/Desktop/demo/src/data.sqlite";
            // create a connection to the database

            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            statmt = conn.createStatement();
            resSet= statmt.executeQuery("SELECT DISTINCT " + first + "," + second + " FROM source_data");

            while (resSet.next()) {
                obj = new JSONObject();
                obj.put(first, resSet.getString(first));
                ar.add(obj);
                obj.put(second, resSet.getString(second));
                ar.add(obj);

               //info suka = new info();
              // suka.a = resSet.getString(first);
              // suka.b = resSet.getString(second);
               arr.add(resSet.getString(first) + "   " + resSet.getString(second));
               //obj.accumulate(first, (obj.put(first, resSet.getString(first))));
               //obj.accumulate(second, resSet.getString(second));
               //obj.put(second, resSet.getString(second));
            }

            System.out.println(ar);

            //System.out.println(arr);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return arr;
    }

    class info{
        String a;
        String b;
    }
}
