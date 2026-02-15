package com.winwell.app;

import java.io.Serializable;

/**
 * Message represents a single chat entry.
 * Implements Serializable to allow data passing between activities if needed.
 */
public class Message implements Serializable {
    private String content;
    private boolean isUser;
    private long timestamp;

    public Message(String content, boolean isUser) {
        this.content = content;
        this.isUser = isUser;
        this.timestamp = System.currentTimeMillis();
    }

    public String getContent() {
        return content;
    }

    public boolean isUser() {
        return isUser;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
