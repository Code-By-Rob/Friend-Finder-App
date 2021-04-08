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

    // Members
    private List<String> interests;
    private Context context;
    private int layout;

    // Constructor
    // This constructor is necessary for RecyclerViews to show the content you store in a data structure.
    // These are constructed and set to a RecyclerView via the .setAdapter(...) method.
    public InterestsAdapter (List<String> interests, Context context, int layout) {
        this.interests = interests;
        this.context = context;
        this.layout = layout;
    }

    // The below is where the view is inflated and returned to the RecyclerView to provide the multiple object and scrollable functionality.
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

    /*
    This class is a ViewHolder which is used to initialise a view and allow for the Custom Adapter class to
    use the above method onBindViewHolder to set the data to multiple initialised variables.
    In this case it is only one. However, good practice.
    Hopefully, it's more or less self-explanatory. If not ask me any questions of Discord.
     */

    public class InterestsViewHolder extends RecyclerView.ViewHolder {

        private TextView interest;

        public InterestsViewHolder(@NonNull View itemView) {
            super(itemView);
            interest = itemView.findViewById(R.id.textInterest);
        }
    }
}
