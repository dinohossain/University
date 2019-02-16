package com.dating.app.idateu;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnector

    {

    public ResultSet loadImage() {


        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://172.31.82.74:3306/idateu";

            Connection conn = DriverManager.getConnection(url, "root", "Admin123");

            Statement select = conn.createStatement();
            PreparedStatement statement = null;
            String query = "SELECT * FROM user WHERE user_ID = '1'";


            statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            statement.close();
            rs.close();


            return rs;}

            catch (Exception e) {
            e.printStackTrace();
            Log.d(" SELECT error", "Failed to retrieve record from database");
                return null;}

    }


}
