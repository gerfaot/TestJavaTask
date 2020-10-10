package com.example.demo.service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;

@Service
public class ServiceTable {
    ArrayList<String> arr = new ArrayList<>();
    JSONArray ar = new JSONArray();
    JSONObject obj;

    public static Statement statmt;
    public static ResultSet resSet;

    public ArrayList<String> testFun(String first, String second) {
        connect(first, second);
        return (arr);
    }

    public void connect(@NotNull String first, @NotNull String second) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/data.sqlite";
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

               arr.add(resSet.getString(first) + "   " + resSet.getString(second));

            }


            //System.out.println(path);
            System.out.println(ar);

            //;

        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

}
