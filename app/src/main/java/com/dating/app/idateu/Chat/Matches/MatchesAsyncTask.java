package com.dating.app.idateu.Chat.Matches;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.dating.app.idateu.UserDetail;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MatchesAsyncTask extends AsyncTask<Void, Void, ArrayList<MatchesObject>> {
    private Connection conn;

    @Override
    protected ArrayList<MatchesObject> doInBackground(Void... voids) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://172.31.82.74:3306/idateu";
            conn = DriverManager.getConnection(url, "root", "Admin123");
            Statement select = conn.createStatement();
        }
        catch (Exception ignored) { }

        try{
            String query = "SELECT user.user_ID, user.profile_pic, user.name FROM user INNER JOIN matches ON matches.matches_ID=user.user_ID WHERE matches.user_ID=" + UserDetail.getUser_id() +
                    "  UNION SELECT user.user_ID, user.profile_pic, user.name FROM user INNER JOIN matches ON matches.user_ID=user.user_ID WHERE matches.matches_ID=" + UserDetail.getUser_id();
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            ArrayList<MatchesObject> matchesObjects = new ArrayList<>();
            while (rs.next()) {
                Blob picTemp = (rs.getBlob(2));
                byte[] bdata = picTemp.getBytes(1, (int) picTemp.length());

                matchesObjects.add(
                        new MatchesObject(
                                rs.getInt(1),
                                rs.getString(3),
                                BitmapFactory.decodeByteArray(bdata, 0 ,bdata.length)
                        )
                );
            }

            return matchesObjects;
        } catch (Exception ignored) { }

        return null;
    }
}
