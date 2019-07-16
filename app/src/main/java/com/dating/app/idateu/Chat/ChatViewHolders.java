package com.dating.app.idateu.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dating.app.idateu.Homepage.Pop_up.Pop_up;
import com.dating.app.idateu.R;
import com.dating.app.idateu.matchesDetail;

public class ChatViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView mMessage;
    public LinearLayout mContainer;

    public ChatViewHolders(View itemView) {
        super (itemView);
        itemView.setOnClickListener(this);

        mMessage = itemView.findViewById((R.id.message));
        mContainer = itemView.findViewById((R.id.container));
    }


    @Override
    public void onClick(View view){

    }

}
