package com.winwell.app;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * BotMessageViewHolder holds the references to the UI elements for a bot
 * message.
 * This pattern optimizes list performance by avoiding repeated findViewById
 * calls.
 */
public class BotMessageViewHolder extends RecyclerView.ViewHolder {
    public TextView MessageBody;
    public TextView MessageTime;

    public BotMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        MessageBody = itemView.findViewById(R.id.text_message_body);
        MessageTime = itemView.findViewById(R.id.text_message_time);
    }

    public void bind(Message message) {
        MessageBody.setText(message.Content);
        // Format and display the timestamp for a realistic chat feel
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
        MessageTime.setText(sdf.format(new Date(message.Timestamp)));
    }
}
