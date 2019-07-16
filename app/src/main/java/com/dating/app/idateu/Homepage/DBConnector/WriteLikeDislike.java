package com.dating.app.idateu.Homepage.DBConnector;

import com.dating.app.idateu.UserDetail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class WriteLikeDislike
    {
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

        public WriteLikeDislike(int matchID, char status)
            {
                try
                {
                    String nStatus = (status == 'l')? "like" : "dislike";
                    String query = "INSERT INTO like_dislike (user_ID,matches_ID,status) " +
                            "VALUES (?,?,?)";
                    statement = connectionSetup();
                    statement = conn.prepareStatement(query);
                statement.setInt(1, UserDetail.getUser_id());
                statement.setInt(2,matchID);
                statement.setString(3,nStatus);
                statement.executeUpdate();
                }
                catch (Exception e)
                {
                }
            }

    }
