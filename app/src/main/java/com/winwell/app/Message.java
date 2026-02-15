package com.winwell.app;

import java.io.Serializable;

/**
 * Message represents a single chat entry.
 * Implements Serializable to allow data passing between activities if needed.
 */
public class Message implements Serializable {
    public String Content;
    public boolean IsUser;
    public long Timestamp;

    public Message(String content, boolean isUser) {
        Content = content;
        IsUser = isUser;
        Timestamp = System.currentTimeMillis();
    }
}
