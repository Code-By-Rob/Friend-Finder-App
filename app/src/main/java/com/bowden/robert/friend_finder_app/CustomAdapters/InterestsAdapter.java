package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bowden.robert.friend_finder_app.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InterestsAdapter extends RecyclerView.Adapter<InterestsAdapter.InterestsViewHolder> {

    private List<String> interests;
    private Context context;
    private int layout;

    public InterestsAdapter (List<String> interests, Context context, int layout) {
        this.interests = interests;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public InterestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new InterestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestsViewHolder holder, int position) {
        holder.interest.setText(interests.get(position));
    }

    @Override
    public int getItemCount() {
        return interests.size();
    }

    public class InterestsViewHolder extends RecyclerView.ViewHolder {

        private TextView interest;

        public InterestsViewHolder(@NonNull View itemView) {
            super(itemView);
            interest = itemView.findViewById(R.id.textInterest);
        }
    }
}
