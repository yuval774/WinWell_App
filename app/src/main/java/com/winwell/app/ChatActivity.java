package com.winwell.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

/**
 * ChatActivity is the main chat screen of the app.
 * It displays a conversation between the user and the WinWell bot using a RecyclerView.
 * The user types a message, the bot responds with a random wellness-related reply after a short delay.
 */
public class ChatActivity extends AppCompatActivity {

    // UI elements - the chat list, input field, and send button
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private EditText editMessage;
    private ImageButton btnSend;

    // A collection of pre-written bot responses about wellness, scheduling, and self-care.
    // The bot picks one randomly each time the user sends a message.
    private final String[] botAnswers = {
            "I've reviewed your calendar for today and noticed a 4-hour meeting block this afternoon; you will need a recovery moment to stay sharp.",
            "Your schedule is packed between 1 PM and 5 PM. Shall I inject a 10-minute buffer to protect your energy?",
            "You have back-to-back calls with no breaks. I suggest a 5-minute deep breathing session at 2:15 PM.",
            "I detected a high-intensity work block. Would you like to switch to 'Auto-pilot' so I can automatically defend your recovery time?",
            "To prevent burnout later today, I recommend moving your 4 PM task to tomorrow morning when your energy is typically higher.",
            "You've been in 'focus mode' for three hours. Let's take a quick walk to reset your clarity.",
            "I see a gap in your schedule at 11 AM. I'm marking this as 'Protected Time' to ensure you don't get booked.",
            "Your Tuesday looks significantly heavier than usual. Let's plan two micro-breaks now so you don't crash later.",
            "I noticed you haven't had a bio-break in 4 hours. Prioritizing your physical needs is key to sustaining this pace.",
            "That was a long sprint. I've queued up a 5-minute hydration and stretch reminder.",
            "I noticed you declined the last two break suggestions. On a scale of 1-10, how is your energy level right now?",
            "You seem to be pushing hard today. Are you running on adrenaline or genuine energy?",
            "Let's check in—not on what you have to do, but on how you feel.",
            "It's been a chaotic morning. Do you need a moment of calm, or are you ready to power through?",
            "You mentioned feeling frayed yesterday. How is your headspace starting out today?",
            "Your current pace matches your 'Burnout Zone' patterns. Let's dial it back slightly to maintain long-term stamina.",
            "I'm sensing low focus based on your task switching. Is it time for a recharge or just a change of scenery?",
            "Do you feel like you are controlling your day, or is the day controlling you right now?",
            "Let's prioritize: What is the one thing that must happen today for you to feel successful and calm?",
            "Recovery isn't laziness; it's fuel. How can we refuel you in the next 15 minutes?",
            "Suggested Activity: 10-minute Deep Breathing Session.",
            "Suggested Activity: A quick 5-minute walk outside to get sunlight and reset your circadian rhythm.",
            "Suggested Activity: 'Box Breathing' technique—inhale for 4, hold for 4, exhale for 4.",
            "Suggested Activity: Step away from the screen and focus on an object 20 feet away for 20 seconds.",
            "Suggested Activity: A quick hydration break. Drink a full glass of water before your next Zoom.",
            "Suggested Activity: 15-minute 'No-Tech' zone. Put the phone down and just sit.",
            "Suggested Activity: Write down three things that went well today to shift your mindset.",
            "Suggested Activity: A 10-minute power nap to restore cognitive alertness.",
            "Suggested Activity: Stretching your neck and shoulders to release the tension from sitting.",
            "Suggested Activity: Call a friend for 5 minutes—social connection is a powerful recovery tool.",
            "I've switched to Co-pilot mode; I will wait for your approval before changing any calendar events.",
            "Understood. I will decline that break, but please promise me you'll leave your desk by 5 PM.",
            "Switching to Auto-pilot. I will now automatically decline conflicting meetings to protect your focus time.",
            "I see you declined the breathing exercise. Would you prefer a quick physical stretch instead?",
            "You've accepted 3 breaks in a row! Great job prioritizing your sustainability.",
            "I've locked your calendar for the next hour as requested. No notifications will get through.",
            "Would you like me to take the lead on rescheduling your low-priority calls today?",
            "I've noted that you prefer 'Deep Breathing' over 'Meditation.' I'll update my suggestions.",
            "You are in control. Just hit 'Decline' if this suggestion doesn't fit your flow right now.",
            "I'm here to help you win on your terms. Adjust the slider if you need more or less intervention.",
            "Welcome to WinWell. I'm here to help you win at life, on your terms.",
            "To get started, please connect your calendar so I can identify your pressure points.",
            "I've successfully synced with your Google Calendar. Scanning for burnout risks now...",
            "Hi there! I'm your performance and well-being companion.",
            "My goal is to ensure you never have to choose between your ambitions and your health.",
            "Your schedule is now protected. I'll notify you when I find gaps for recovery.",
            "Let's set some boundaries. What time do you absolutely need to shut down today?",
            "I am not just a calendar tool; I am designed to understand your capacity.",
            "Everything looks set! Entering Chat mode to begin your journey.",
            "Remember, I learn from you. The more we chat, the better I can protect your energy."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge so the app uses the full screen
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        // Adjust padding so our content doesn't hide behind the system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Connect the XML views to our Java variables
        recyclerView = findViewById(R.id.recycler_chat);
        editMessage = findViewById(R.id.edit_message);
        btnSend = findViewById(R.id.btn_send);

        // Create the adapter that will manage and display our chat messages
        chatAdapter = new ChatAdapter();

        // Set up the RecyclerView with a LinearLayoutManager so messages appear in a vertical list
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chatAdapter);

        // Show a welcome message from the bot when the chat screen opens
        AddBotMessage("Welcome to WinWell! How can I help you today?");

        // Set up the send button - when clicked, it takes the user's text and sends it
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editMessage.getText().toString().trim();
                // Only send the message if the user actually typed something
                if (!content.isEmpty()) {
                    SendMessage(content);
                }
            }
        });
    }

    /**
     * Handles sending a user message and triggering a bot response.
     * First adds the user's message to the chat, then after a 1-second delay
     * the bot responds with a random answer to simulate a real conversation.
     */
    private void SendMessage(String content) {
        // Add the user's message to the chat and scroll down to show it
        chatAdapter.AddMessage(new Message(content, true));
        recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);

        // Clear the input field so the user can type a new message
        editMessage.setText("");

        // Use a Handler to delay the bot's response by 1 second,
        // this makes it feel like the bot is actually "thinking"
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Pick a random response from the bot's answer pool
                String randomAnswer = botAnswers[new Random().nextInt(botAnswers.length)];
                AddBotMessage(randomAnswer);
            }
        }, 1000);
    }

    /**
     * Adds a bot message to the chat and scrolls the RecyclerView to the bottom
     * so the user can see the new message right away.
     */
    private void AddBotMessage(String content) {
        chatAdapter.AddMessage(new Message(content, false));
        recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
    }
}
