package com.winwell.app;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * ViewHolder for user messages in the chat.
 * It holds references to the text and time views inside the user's chat bubble.
 * Using a ViewHolder improves performance because we don't need to call
 * findViewById every time a message scrolls into view.
 */
public class UserMessageViewHolder extends RecyclerView.ViewHolder {

    // The TextView that displays the message text
    public TextView MessageBody;

    // The TextView that displays the timestamp
    public TextView MessageTime;

    // Constructor - finds and stores references to the views in the layout
    public UserMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        MessageBody = itemView.findViewById(R.id.text_message_body);
        MessageTime = itemView.findViewById(R.id.text_message_time);
    }

    /**
     * Fills in the message data into the views.
     * Sets the message text and formats the timestamp to show
     * a readable time like "3:45 PM".
     */
    public void bind(Message message) {
        MessageBody.setText(message.Content);
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
        MessageTime.setText(sdf.format(new Date(message.Timestamp)));
    }
}
