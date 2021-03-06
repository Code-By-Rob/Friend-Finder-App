package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bowden.robert.friend_finder_app.ChatFragment;
import com.bowden.robert.friend_finder_app.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WhosNewAdapter extends RecyclerView.Adapter<WhosNewAdapter.WhosNewViewHolder> {

    /*
     * WhosNewAdapter is used with the chat fragment.
     * The fragment can be found @ res > layout > fragment_chat.xml.
     * It will only be necessary to access these layout files if you are curious of the id for the front-end objects.
     */
    // Members
    private List<Integer> newMatches;
    private Context context;
    private int layout;

    // Constructor
    public WhosNewAdapter(List<Integer> newMatches, Context context, int layout) {
        this.newMatches = newMatches;
        this.context = context;
        this.layout = layout;
    }

    // Necessary Methods
    @NonNull
    @Override
    public WhosNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new WhosNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhosNewViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(newMatches.get(position))
                .into(holder.newMatch);

    }

    @Override
    public int getItemCount() {
        return newMatches.size();
    }

    // ViewHolder Class - Used to hold the views that are set data via onBindViewHolder.
    public class WhosNewViewHolder extends RecyclerView.ViewHolder {

        private ImageView newMatch;

        public WhosNewViewHolder(@NonNull View itemView) {
            super(itemView);
            newMatch = itemView.findViewById(R.id.imageWhosNew);
        }
    }
}
