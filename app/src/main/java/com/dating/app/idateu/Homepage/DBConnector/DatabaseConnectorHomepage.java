package com.dating.app.idateu.Homepage.DBConnector;

import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectorHomepage

    {
    public StoreResult loadImage(int index) {
        StoreResult storage;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://172.31.82.74:3306/idateu";

            conn = DriverManager.getConnection(url, "root", "Admin123");

            Statement select = conn.createStatement();
            statement = null;
            String query = "SELECT * FROM user WHERE user_ID ="+index;


            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            String[] temp = new String[2];
            Blob temp1;
            InputStream is =null;
            String response = null;
            while (rs.next())
                {
                temp1=(rs.getBlob(2));
                is = temp1.getBinaryStream();
                response = convertStreamToString(is);
                temp[0]=(rs.getString(3));
                temp[1]=(rs.getString(7));
                }
            storage = new StoreResult(response,temp[0],temp[1]);
            }
        catch (Exception e)
            {
            e.printStackTrace();
            Log.d(" SELECT error", "Failed to retrieve record from database");
                return null;
            }

         finally
            {
            if (rs != null) try
                {
                rs.close();
                }
                catch (SQLException ignore)
                {
                }
            if (statement != null) try
                {
                statement.close();
                }
                catch (SQLException ignore)
                {
                }
            if (conn != null)
                try
                {
                conn.close();
                }
            catch (SQLException ignore)
                {
                }
            }
        return storage;}

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

    }