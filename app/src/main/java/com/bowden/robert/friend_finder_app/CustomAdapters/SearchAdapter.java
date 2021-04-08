package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowden.robert.friend_finder_app.R;
import com.bowden.robert.friend_finder_app.TestClasses.SearchFriendTest;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends ArrayAdapter<SearchFriendTest> {

    // Members
    private List<SearchFriendTest> friends;
    private Context context;
    private int layout;

    public SearchAdapter (Context context, int layout, List<SearchFriendTest> friends) {
        super(context, layout, friends);
        this.friends = friends;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        SearchViewHolder holder;

        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(layout, parent, false);
            holder = new SearchViewHolder();
            // initialise props
            itemView = setSearchProps(itemView, holder);
            itemView.setTag(holder);
        } else {
            holder = (SearchViewHolder)itemView.getTag();
        }
        // set the props
        setPropResources(position, holder);
        //return the View
        return itemView;
    }

    private View setSearchProps(View itemView, SearchViewHolder holder) {
        holder.searchPersonImage = itemView.findViewById(R.id.searchPersonImage);
        holder.searchPersonMeme = itemView.findViewById(R.id.searchBestMeme);
        holder.searchPersonBio = itemView.findViewById(R.id.searchBiography);
        holder.searchPersonName = itemView.findViewById(R.id.searchPersonName);
        holder.searchPersonInterests = itemView.findViewById(R.id.interestsRecycler);
        return itemView;
    }
    private void setPropResources(int position, SearchViewHolder holder) {
        holder.searchPersonImage.setImageResource(friends.get(position).getImageResourcePP());
        holder.searchPersonMeme.setImageResource(friends.get(position).getImageResourceMeme());
        holder.searchPersonBio.setText(friends.get(position).getPersonBio());
        holder.searchPersonName.setText(friends.get(position).getPersonName() + ", " +
                friends.get(position).getPersonAge());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.searchPersonInterests.setLayoutManager(layoutManager);
        InterestsAdapter adapter = new InterestsAdapter(friends.get(position).getPersonInterests(), context, R.layout.layout_interest_item);
        holder.searchPersonInterests.setAdapter(adapter);
    }
    static class SearchViewHolder {
        // Props - These are the props for the search layout; i.e. anything that will change
        ImageView searchPersonImage;
        ImageView searchPersonMeme;
        TextView searchPersonBio;
        TextView searchPersonName;
        RecyclerView searchPersonInterests;
    }
}
