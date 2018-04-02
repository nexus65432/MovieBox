package com.android.project.moviebox.DataModels;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.project.moviebox.R;

public class CardViewHolder extends RecyclerView.ViewHolder {

    public TextView cardId;
    public ImageView mCardImage;
    public TextView mCardTitle;

    public CardViewHolder(View itemView) {
        super(itemView);
        mCardTitle = itemView.findViewById(R.id.card_title);
        cardId = itemView.findViewById(R.id.card_id);
        mCardImage = itemView.findViewById(R.id.card_image_view);
    }
}
