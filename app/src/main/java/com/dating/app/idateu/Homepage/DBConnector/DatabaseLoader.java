package com.dating.app.idateu.Homepage.DBConnector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


import com.dating.app.idateu.matchesDetail;
import com.dating.app.idateu.UserDetail;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.microedition.khronos.opengles.GL10;


public class DatabaseLoader {
    private static ResultSet rs;
    private static Connection conn;
    private static PreparedStatement statement;

    public static PreparedStatement connectionSetup()
        {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://172.31.82.74:3306/idateu";
            conn = DriverManager.getConnection(url, "root", "Admin123");
            Statement select = conn.createStatement();
            statement = null;
            }
        catch (Exception e)
            {
            }
        return statement;
        }

    public static matchesDetail addData(int i) {
        matchesDetail singleData = null;
        String lBoundMySQL = String.valueOf(i-1);
        String uBoundMySQL = String.valueOf(i);
            try{
                String query = "SELECT * FROM user LIMIT "+lBoundMySQL+","+uBoundMySQL; //will load one match at a time
                statement = connectionSetup();
                statement = conn.prepareStatement(query);
                rs = statement.executeQuery();
                if (rs.wasNull())
                    {
                    singleData = new matchesDetail("No_connection");
                    return singleData;
                    }
                if (!moreDataAvailable()) {
                    singleData = new matchesDetail("End");
                    return singleData;}
                int mID;
                String[] tempString = new String[3];
                Blob picTemp;
                Date tempDob;
                InputStream is = null;
                String response = null;
                if (MatchingAlgorithm.isSuitableMatch(rs.getString(5), //will only select the relevant match
                        rs.getString(6)) && UserDetail.getUser_id() != rs.getInt(1)) //does not make 'itself' show up
                    {
                    picTemp = (rs.getBlob(2));
                    byte[] bdata = picTemp.getBytes(1, (int) picTemp.length());
                    response = Base64.encodeToString(bdata, Base64.DEFAULT);
                    mID = rs.getInt(1);
                    tempString[0] = (rs.getString(3)); //name
                    tempString[1] = (rs.getString(6)); //orientation
                    tempString[2] = (rs.getString(7)); //bio
                    tempDob = rs.getDate(4);
                    singleData = new matchesDetail(mID,response, tempString[0],tempDob ,tempString[1],tempString[2]);
                    }
                else
                    {
                    singleData = new matchesDetail("unsuitable match");
                    }
            } catch (Exception e) {
            }
                return singleData; // returns one record
    }


        private static boolean moreDataAvailable() {
            try {
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }


    public static Bitmap image(String picData)
        {
            try
                {
                InputStream in = null;
                String stringToByte = picData;
                byte[] imageArray = Base64.decode(stringToByte, Base64.DEFAULT);
                Blob blob = new SerialBlob(imageArray);
                in = blob.getBinaryStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                if ((bmp.getHeight())> GL10.GL_MAX_TEXTURE_SIZE
                        || bmp.getWidth()>GL10.GL_MAX_TEXTURE_SIZE)
                {
                    int nh = (int) ( bmp.getHeight() * (512.0 / bmp.getWidth()) );
                    bmp = Bitmap.createScaledBitmap(bmp, 512, nh, true);
                }
                return bmp;
                }
            catch (Exception e)
                {
                return null;
                }
        }

    public static void clearTemp()
        {
        if (rs != null) try {
            rs.close();
        } catch (SQLException ignore) {
        }
        if (statement != null) try {
            statement.close();
        } catch (SQLException ignore) {
        }
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
    }