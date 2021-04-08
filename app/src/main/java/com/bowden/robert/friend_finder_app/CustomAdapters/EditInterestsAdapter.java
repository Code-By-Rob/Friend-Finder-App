package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.bowden.robert.friend_finder_app.R;

import java.util.List;

public class EditInterestsAdapter extends BaseAdapter {

    /*
    * This is the adapter that fills the GridView in "fragment_profile_edit.xml".
    * It takes the list of interests, the Type can be changed as you see fit.
    * It currently takes in a String and sets that string to a layout for each individual interest;
    * That layout is called "layout_edit_interests_item.xml" and is found in the layout directory.
    */

    // Members
    private List<String> interests;
    private Context context;
    private LayoutInflater layoutInflater;
    // Prop
    private Button interestButton;

    // The constructor is used to create the adapter which is set to the GridView.
    public EditInterestsAdapter (List<String> interests, Context context) {
        this.interests = interests;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // The below four methods are required for the adapter.
    @Override
    public int getCount() {
        return interests.size();
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
            convertView = layoutInflater.inflate(R.layout.layout_edit_interests_item, parent, false);
        }
        interestButton = convertView.findViewById(R.id.buttonInterest);
        interestButton.setText(interests.get(position));
        return convertView;
    }
}
