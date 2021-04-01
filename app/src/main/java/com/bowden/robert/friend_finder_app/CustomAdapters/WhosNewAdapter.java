package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bowden.robert.friend_finder_app.R;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WhosNewAdapter extends RecyclerView.Adapter<WhosNewAdapter.WhosNewViewHolder> {

    /*
    * WhosNewAdapter is used with the chat fragment.
    */

    private List<String> newMatches;
    private Context context;
    private int layout;

    public WhosNewAdapter (List<String> newMatches, Context context, int layout) {
        this.newMatches = newMatches;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public WhosNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new WhosNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhosNewViewHolder holder, int position) {
        Glide.with(context.getApplicationContext())
                .load(newMatches.get(position))
                .into(holder.newMatch);
    }

    @Override
    public int getItemCount() {
        return newMatches.size();
    }

    public class WhosNewViewHolder extends RecyclerView.ViewHolder {

        private ImageView newMatch;

        public WhosNewViewHolder(@NonNull View itemView) {
            super(itemView);
            newMatch = itemView.findViewById(R.id.imageWhosNew);
        }
    }
}
