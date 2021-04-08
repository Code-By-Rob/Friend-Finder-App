package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowden.robert.friend_finder_app.R;
import com.bowden.robert.friend_finder_app.TestClasses.FriendsChatTest;

import java.util.List;

public class ChatsAdapter extends BaseAdapter {

    /*
    * This is the layout adapter for the chats fragment.
    * It will take an argument like an ArrayList and use the objects properties to fill
    * the layout's props.
    *
    * The layout property for the chat is in the directory labelled "layout".
    *
    * The layout is called 'layout_chat_item' and has 3 props that need the following attributes:
    *   - String (for the name of the friend they are chatting with)
    *   - String (for the latest text that has been sent/received)
    *   - Image (I would recommend a bitmap as they're easy. However, this can be any type of image resource)
    */

    // Members
    private List<FriendsChatTest> friends;
    private Context context;
    private LayoutInflater layoutInflater;
    // layout_chat_item Props
    private TextView friendName;
    private TextView recentText;
    private ImageView friendImage;

    /*
     * Constructor for the adapter. Used to set the adapter to the LinearLayout.
     * When adding the friend chat elements remember to Change the Type of the List object.
     * The above must also be done for the List member below.
     * Currently the List object is using a test class.
     */
    public ChatsAdapter (List<FriendsChatTest> friends, Context context) {
        this.friends = friends;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // The below method must be the size of the List or other Array style object in order to fill the parent layout
    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    // gets the unique ID of the item/object in the list.
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Set the attributes for the props in this function here.
    // The below method will set all of the information in the above List to their corresponding
    // variables, which sets it to the layout; i.e. the variables
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_chat_item, parent, false);
        }

        friendName = convertView.findViewById(R.id.chatFriendName);
        recentText = convertView.findViewById(R.id.chatRecentText);
        friendImage = convertView.findViewById(R.id.chatFriendImage);

        // use the setter methods, for each of the above variables, to give them their text and image
        friendName.setText(friends.get(position).getFriendName());
        recentText.setText(friends.get(position).getRecentText());
        friendImage.setImageResource(friends.get(position).getFriendimage());
        // The above is how you should link the items to their layout.

        return convertView;
    }
    // If you have any questions about linking the backend to the adapters, drop me a message on Discord.
}
