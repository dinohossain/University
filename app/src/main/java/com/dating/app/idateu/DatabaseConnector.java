package com.dating.app.idateu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector

    {

        ImageView current_match;


        public DI loadImage() {
            DI biler = new DI();
            Connection conn = null;
            PreparedStatement statement = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://172.31.82.74:3306/idateu";

                conn = DriverManager.getConnection(url, "root", "Admin123");

                Statement select = conn.createStatement();
                statement = null;
                String query = "SELECT * FROM user WHERE user_ID = '1'";


                statement = conn.prepareStatement(query);
                rs = statement.executeQuery();
                while (rs.next()) {
                biler.setProfilePic(rs.getBlob(2));}


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
            return biler;}

    }



