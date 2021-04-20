package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowden.robert.friend_finder_app.R;
import com.bowden.robert.friend_finder_app.SearchFragment;
import com.bowden.robert.friend_finder_app.ServerClasses.Profile;
import com.bowden.robert.friend_finder_app.TestClasses.SearchFriendTest;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends ArrayAdapter<Profile> {

    // Members
    private List<Profile> friends;
    private Context context;
    private int layout;

    // The constructor
    public SearchAdapter (Context context, int layout, List<Profile> friends) {
        super(context, layout, friends);
        this.friends = friends;
        this.context = context;
        this.layout = layout;
    }

    // Necessary methods
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

    // This method is used to initialise the property variables of the layout file 'layout_search_item.xml'.
    private View setSearchProps(View itemView, SearchViewHolder holder) {
        holder.searchPersonImage = itemView.findViewById(R.id.searchPersonImage);
        holder.searchPersonMeme = itemView.findViewById(R.id.searchBestMeme);
        holder.searchPersonBio = itemView.findViewById(R.id.searchBiography);
        holder.searchPersonName = itemView.findViewById(R.id.searchPersonName);
        holder.searchPersonInterests = itemView.findViewById(R.id.interestsRecycler);
        return itemView;
    }
    /*
     This method sets the resources from a TextClass found in the appropriate directory.
     Also, I understand the warning behind the concat of Strings for a TextView. However,
     I have not had the energy to change it :/
     */
    private void setPropResources(int position, SearchViewHolder holder) {
//        Glide.with(context)
//                .load(friends.get(position).getImageBitmap())
//                .into(holder.searchPersonImage);
//        Glide.with(context)
//                .load(friends.get(position).getImageBitmap())
//                .into(holder.searchPersonMeme);
        holder.searchPersonImage.setImageBitmap(friends.get(position).getImage1Bitmap());
        holder.searchPersonMeme.setImageBitmap(friends.get(position).getImage1Bitmap());
        holder.searchPersonBio.setText(friends.get(position).getBio());
        holder.searchPersonName.setText(friends.get(position).getNamefirst() + ", " +
                friends.get(position).getNamelast());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.searchPersonInterests.setLayoutManager(layoutManager);
        InterestsAdapter adapter = new InterestsAdapter(friends.get(position).getInterests(), context, R.layout.layout_interest_item);
        holder.searchPersonInterests.setAdapter(adapter);
    }
    // This is the ViewHolder class I spoke about in the InterestsAdapter. Good practice.
    static class SearchViewHolder {
        // Props - These are the props for the search layout; i.e. anything that will change
        ImageView searchPersonImage;
        ImageView searchPersonMeme;
        TextView searchPersonBio;
        TextView searchPersonName;
        RecyclerView searchPersonInterests;
    }
}
