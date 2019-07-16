package com.dating.app.idateu.SignUp_LogIn.DBConnection;

import com.dating.app.idateu.UserDetail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnectorLogIn
    {
            static int user_id = 0;
            public static String email;
            public static String password;

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

            public DatabaseConnectorLogIn(String email, String password)
                {
                connectionSetup();
                DatabaseConnectorLogIn.email = email;
                DatabaseConnectorLogIn.password = password;
                }

            //0 --> email not in DB, 1 --> wrong password, 2--> login success
            //  -1 --> unable to connect to the server
            public static int loginUser()
                {
                if(!canAppReceiveData()) return -1;
                else if (emailExist()
                    &&correctPassword())
                    {
                    setUserDetail();
                    return 2;
                    }
                else if (emailExist()
                        &&!correctPassword()) return 1;
                return 0;
                }

        private static boolean canAppReceiveData()
            {
                try {
                    Statement select = conn.createStatement();
                    statement = null;
                    rs = conn.getMetaData().getCatalogs();
                    while (rs.next()) {
                        String databaseName = rs.getString(1);
                        if(!databaseName.isEmpty())return true;
                    }
                }
                catch(Exception e)
                {

                }
                return false;
            }

        private static boolean correctPassword()
            {
            try {
                String query = "SELECT Password FROM logInDetails WHERE Email ="+"'"+email+"'";
                statement = conn.prepareStatement(query);
                rs = statement.executeQuery();
                while (rs.next())
                {
                if (rs.getString(1).equals(password)) return true;
                }
            }
            catch(Exception e)
                {
                }
            return false;
            }

        public static boolean emailExist()
                {
                    try {
                        String query = "SELECT * FROM logInDetails WHERE Email ="+"'"+email+"'";
                        statement = conn.prepareStatement(query);
                        rs = statement.executeQuery();
                        if (rs.next())
                            {
                            user_id = rs.getInt(1);
                            return true;
                            }
                        }
                        catch(Exception e)
                            {
                            }
                return false;
                }

        private static void setUserDetail()
        {
            try {
                String query = "SELECT * FROM user WHERE user_ID ="+"'"+user_id+"'";
                statement = conn.prepareStatement(query);
                rs = statement.executeQuery();
                if (rs.next())
                    {
                    UserDetail.setUser_id(rs.getInt(1));
                    UserDetail.setUsers_name(rs.getString(3));
                    UserDetail.setDob(rs.getDate(4));
                    UserDetail.setGender(rs.getString(5));
                    UserDetail.setOrientation(rs.getString(6));
                    UserDetail.setBio(rs.getString(7));
                    }
                }catch (Exception e)
                {
                }
        }
    }