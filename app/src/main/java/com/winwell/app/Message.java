package com.winwell.app;

import java.io.Serializable;

/**
 * Message represents a single chat message in the conversation.
 * It stores the text, who sent it (user or bot), and when it was sent.
 * Implements Serializable so we can pass Message objects between activities if needed.
 */
public class Message implements Serializable {

    // The actual text content of the message
    public String Content;

    // true = sent by the user, false = sent by the bot
    public boolean IsUser;

    // The time the message was created, stored as milliseconds
    public long Timestamp;

    // Constructor - creates a new message with the given text and sender type,
    // and automatically records the current time
    public Message(String content, boolean isUser) {
        Content = content;
        IsUser = isUser;
        Timestamp = System.currentTimeMillis();
    }
}
