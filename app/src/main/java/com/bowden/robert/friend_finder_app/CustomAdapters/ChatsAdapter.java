package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowden.robert.friend_finder_app.R;

import java.util.List;

public class ChatsAdapter extends BaseAdapter {

    /*
    * This is the layout adapter for the chats fragment.
    * It will take an argument like an ArrayList and use the objects properties to fill
    * the layout's props.
    *
    * The layout is called 'layout_chat_item' and has 3 props that need the following attributes:
    *   - String (for the name of the friend they are chatting with)
    *   - String (for the latest text that has been sent/received)
    *   - Image (I would recommend a bitmap. However, this can be any type of image resource)
    */

    // Members
    private List<Object> friends;
    private Context context;
    private LayoutInflater layoutInflater;
    // layout_chat_item Props
    private TextView friendName;
    private TextView recentText;
    private ImageView friendImage;

    /*
     * Constructor for the adapter. Used to set the adapter to the LinearLayout.
     * When adding the friend chat elements remember to chat the type of the List object.
     * The above must also be done for the List member.
     */
    public ChatsAdapter (List<Object> friends, Context context) {
        this.friends = friends;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override // could be used to get the chat service of a specific friend?
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Set the attributes for the props in this function here.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_chat_item, parent, false);
        }

        friendName = convertView.findViewById(R.id.chatFriendName);
        recentText = convertView.findViewById(R.id.chatRecentText);
        friendImage = convertView.findViewById(R.id.chatFriendImage);

        // use the setter methods, for each of the above variables, to give them their text and image

        return convertView;
    }
}
