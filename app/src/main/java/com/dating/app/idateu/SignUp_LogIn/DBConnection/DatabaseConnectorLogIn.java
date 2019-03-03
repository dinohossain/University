package com.dating.app.idateu.SignUp_LogIn.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectorLogIn
    {
            String email;
            String password;

            public DatabaseConnectorLogIn(String email, String password)
                {
                this.email = email;
                this.password = password;
                }

            //0 --> email not in DB, 1 --> wrong password, 2--> login success
            //  -1 --> unable to connect to the server
            public int loginUser()
                {
                if(!canAppReceiveData()) return -1;
                else if (emailExist()
                    &&correctPassword()) return 2;
                else if (emailExist()
                        &&!correctPassword()) return 1;
                return 0;
                }

        private boolean canAppReceiveData()
            {
                try {
                    Connection conn = null;
                    PreparedStatement statement = null;
                    ResultSet rs = null;
                    Class.forName("com.mysql.jdbc.Driver");
                    DriverManager.setLoginTimeout(5);
                    String url = "jdbc:mysql://172.31.82.74:3306/idateu";
                    conn = DriverManager.getConnection(url, "root", "Admin123");
                    Statement select = conn.createStatement();
                    statement = null;
                    ResultSet resultSet = conn.getMetaData().getCatalogs();
                    while (resultSet.next()) {
                        String databaseName = resultSet.getString(1);
                        return true;
                    }

                }
                catch(Exception e)
                {

                }
                return false;
            }


        private boolean correctPassword()
            {
            Connection conn = null;
            PreparedStatement statement = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://172.31.82.74:3306/idateu";
                conn = DriverManager.getConnection(url, "root", "Admin123");
                Statement select = conn.createStatement();
                statement = null;

                String query = "SELECT Password FROM logIn_details WHERE Email ="+"'"+email+"'";
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

        public boolean emailExist()
                {
                Connection conn = null;
                PreparedStatement statement = null;
                ResultSet rs = null;
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://172.31.82.74:3306/idateu";
                        conn = DriverManager.getConnection(url, "root", "Admin123");
                        Statement select = conn.createStatement();
                        statement = null;

                        String query = "SELECT * FROM logIn_details WHERE Email ="+"'"+email+"'";
                        statement = conn.prepareStatement(query);
                        rs = statement.executeQuery();
                        if (rs.next())
                            {
                            return true;
                            }
                        }
                        catch(Exception e)
                            {

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
                return false;
                }
    }

