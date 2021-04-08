package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bowden.robert.friend_finder_app.R;
import com.bowden.robert.friend_finder_app.TestClasses.FriendsChatTest;

import java.util.List;

public class FriendsAdapter extends BaseAdapter {

    // Members
    private List<FriendsChatTest> friends;
    private Context context;
    private LayoutInflater layoutInflater;
    // Prop
    private ImageView friendImage;


    // Constructor
    public FriendsAdapter (List<FriendsChatTest> friends, Context context) {
        this.friends = friends;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Necessary methods (because of the class extending BaseAdapter).
    // Below is the getCount() method this is essential to the adapter so it knows how many Views to inflate.
    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*
     * The below method is where you can assign the props that will be inflated into the ListView found at the bottom of 'fragment_chat.xml'.
     * I typically use a List object. however, this can be whatever data structure you are comfortable with.
     * The only true variable here is the text that will be set on the layout file (the constant) for the buttons.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_friend_item, parent, false);
        }
        friendImage = convertView.findViewById(R.id.friendimage);
        friendImage.setImageResource(friends.get(position).getFriendimage());
        return convertView;
    }
}
