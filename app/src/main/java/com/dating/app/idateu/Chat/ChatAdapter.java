package com.dating.app.idateu.Chat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dating.app.idateu.R;

import java.util.List;

//Populates each every single item_matches.xml
public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolders> {
    private List<ChatObject> chatList;
    private Context context;

    //Variable that passes information from MatchesActivity to here
    public ChatAdapter(List<ChatObject> chatList, Context context){
        this.chatList = chatList;
        this.context = context;
    }



    @Override
    public ChatViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);/*Pass lp inside the layoutView*/

        ChatViewHolders rcv = new ChatViewHolders((layoutView));

        return rcv;

    }

    @Override
    public void onBindViewHolder(ChatViewHolders holder, int position) {
        /*If current user then black on grey*/

        holder.mMessage.setGravity((Gravity.END));
        holder.mMessage.setTextColor(Color.parseColor("#404040")); /*Black text on top of grey*/
        holder.mContainer.setBackgroundColor(Color.parseColor("#F4F4F4"));


        /*If other user sends a text*/
        holder.mMessage.setGravity((Gravity.START));
        holder.mMessage.setTextColor(Color.parseColor("#FFFFFF")); /*Black text on top of grey*/
        holder.mContainer.setBackgroundColor(Color.parseColor("#2DB4C8"));

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
