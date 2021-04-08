package com.bowden.robert.friend_finder_app.CustomAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowden.robert.friend_finder_app.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageListAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Object> messageList; // add the message object here

    public MessageListAdapter (Context context, List<Object> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // creates, assigns, and returns the view that shows in the RecyclerView

//        View view;
//
//        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
//            view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_message_sent, parent, false);
//            return new SentMessageHolder(view);
//        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
//            view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_message_received, parent, false);
//            return new ReceivedMessageHolder(view);
//        }

        return null;
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        // getItemViewType is a code snippet that looks comprehensive enough
        // something similar can be used to check whether the message is 'sent' or 'received'

//        UserMessage message = (UserMessage) mMessageList.get(position);
//
//        if (message.getSender().getUserId().equals(MessageClass.getCurrentUser().getUserId())) {
//            // If the current user is the sender of the message
//            return VIEW_TYPE_MESSAGE_SENT;
//        } else {
//            // If some other user sent the message
//            return VIEW_TYPE_MESSAGE_RECEIVED;
//        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // onBindViewHolder is used to set the view content
        // This example inflates the correct layout dependent on whether message is 'sent' or 'received'
//        UserMessage message = (UserMessage) mMessageList.get(position);
//
//        switch (holder.getItemViewType()) {
//            case VIEW_TYPE_MESSAGE_SENT:
//                ((SentMessageHolder) holder).bind(message);
//                break;
//            case VIEW_TYPE_MESSAGE_RECEIVED:
//                ((ReceivedMessageHolder) holder).bind(message);
//        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.textChatUser_them);
            timeText = itemView.findViewById(R.id.textChatTimestamp_them);
            nameText = itemView.findViewById(R.id.textChatMessage_them);
            profileImage = itemView.findViewById(R.id.imageChatProfile_them);
        }

        void bind(Object message) {
            // set the text like below
//            messageText.setText(message.getMessage());

            // Format the stored timestamp into a readable String using method.
            // e.g.
//            timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
//            nameText.setText(message.getSender().getNickname());

            // Insert the profile image from a URL into the ImageView.
            // e.g.
//            Utils.displayRoundImageFromUrl(context, message.getSender().getProfileUrl(), profileImage);
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.textChatMessage_me);
            timeText = itemView.findViewById(R.id.textChatTimestamp_me);
        }

        void bind(Object message) {
            // Set the text like below
//            messageText.setText(message.getMessage());

            // Format the stored timestamp into a readable String using method.
            // e.g.
//            timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
        }
    }

}
