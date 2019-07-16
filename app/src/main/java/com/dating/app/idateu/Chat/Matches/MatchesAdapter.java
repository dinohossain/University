package com.dating.app.idateu.Chat.Matches;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dating.app.idateu.R;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ExecutionException;

//Populates each every single item_matches.xml
public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolders> {

    private List<MatchesObject> matchesList;
    private Context context;

    //Variable that passes information from MatchesActivity to here
    public MatchesAdapter(List<MatchesObject> matchesList, Context context) {
        this.matchesList = matchesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchesViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);/*Pass lp inside the layoutView*/

        return new MatchesViewHolders((layoutView));
    }

    @Override
    public void onBindViewHolder(MatchesViewHolders holder, int position) {
        MatchesObject object = matchesList.get(position);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        object.getMatchImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
        Glide.with(context)
                .load(stream.toByteArray())
                .error(R.drawable.user_profile_icon)
                .into(holder.mMatchImage);

        holder.mMatchId.setText(object.getUser_ID());
        //holder.mMatchImage.setImageBitmap(object.getMatchImage());
        holder.mMatchName.setText(object.getName());
    }


    @Override
    public int getItemCount() {
        return matchesList.size() ;
    }

}
