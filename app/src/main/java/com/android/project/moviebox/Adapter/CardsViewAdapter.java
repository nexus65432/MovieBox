package com.android.project.moviebox.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.project.moviebox.DataModels.CardTypeMovie;
import com.android.project.moviebox.DataModels.CardViewHolder;
import com.android.project.moviebox.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

public class CardsViewAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private Context mContext;
    private List<JsonObject> mCardObjects;
    private Gson mGson = new Gson();

    public CardsViewAdapter(Context context, @NonNull List<JsonObject> cards) {
        this.mContext = context;
        this.mCardObjects = cards;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_items_layout, parent, false);

        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {

        JsonObject cardDetails = mCardObjects.get(position);
        if (cardDetails != null) {

            CardTypeMovie movieType = mGson.fromJson(cardDetails.toString(), CardTypeMovie.class);
            if (movieType != null) {

                int cardId = movieType.getId();
                if (cardId > 0) {
                    holder.cardId.setText(String.valueOf(cardId));
                } else {
                    holder.cardId.setText("");
                }

                String cardTitle = movieType.getTitle();
                if (cardTitle != null) {
                    holder.mCardTitle.setText(cardTitle);
                }

                String cardImageUrl = movieType.getImage();
                if (cardImageUrl != null) {
                    Glide.with(mContext)
                            .load(cardImageUrl)
                            .centerCrop()
                            .override(90,90)
                            .placeholder(R.drawable.default_movie)
                            .error(R.drawable.default_movie)
                            .into(holder.mCardImage);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        int retVal = 0;
        if (mCardObjects != null) {
            retVal = mCardObjects.size();
        }
        return retVal;
    }
}
