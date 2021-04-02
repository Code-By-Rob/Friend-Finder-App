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

    private List<FriendsChatTest> friends;
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageView friendImage;

    public FriendsAdapter (List<FriendsChatTest> friends, Context context) {
        this.friends = friends;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

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
