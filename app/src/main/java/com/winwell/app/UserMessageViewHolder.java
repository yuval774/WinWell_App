package com.winwell.app;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * UserMessageViewHolder holds the references to the UI elements for a user
 * message.
 * This pattern optimizes list performance by avoiding repeated findViewById
 * calls.
 */
public class UserMessageViewHolder extends RecyclerView.ViewHolder {
    TextView messageBody;
    TextView messageTime;

    public UserMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        messageBody = itemView.findViewById(R.id.text_message_body);
        messageTime = itemView.findViewById(R.id.text_message_time);
    }

    public void bind(Message message) {
        messageBody.setText(message.getContent());
        // Format and display the timestamp for a realistic chat feel
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
        messageTime.setText(sdf.format(new Date(message.getTimestamp())));
    }
}
