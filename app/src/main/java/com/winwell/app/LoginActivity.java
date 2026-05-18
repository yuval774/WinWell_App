package com.winwell.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Firebase Analytics — tracks when a user successfully logs in
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * LoginActivity — the first screen the user sees when they open WinWell.
 *
 * HW3 Firebase feature implemented here:
 *   • Firebase Analytics → logs a LOGIN event every time a user signs in successfully.
 *     Uri: this proves that Analytics is tracking real user behavior (not just app opens).
 *
 * The login credentials are hardcoded for demo purposes:
 *   Email:    uritheteacher@gmail.com
 *   Password: androidstudio
 */
public class LoginActivity extends AppCompatActivity {

    // Firebase Analytics instance — used to log the login event
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display (content goes behind status/nav bars)
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Adjust padding so content stays clear of the system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ── Initialize Firebase Analytics ──────────────────────────────────────
        // Uri: Analytics is initialized here so we can log the login event below.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // ── Connect XML views to Java variables ────────────────────────────────
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton        = findViewById(R.id.login);

        // ── Sign In button click handler ───────────────────────────────────────
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check credentials (hardcoded for the HW3 demo)
                if ("uritheteacher@gmail.com".equals(username) && "androidstudio".equals(password)) {

                    // ── Firebase Analytics: log the LOGIN event ───────────────
                    // This tells Firebase "a user just logged in via email".
                    // Uri: you can see this event appear in the Firebase Analytics dashboard.
                    Bundle loginBundle = new Bundle();
                    loginBundle.putString(FirebaseAnalytics.Param.METHOD, "email");
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, loginBundle);

                    // Create an Intent to navigate to the chat screen
                    Intent intent = new Intent(LoginActivity.this, ChatActivity.class);

                    // Shared element transition: the logo animates smoothly from
                    // this screen into the header of ChatActivity
                    androidx.core.app.ActivityOptionsCompat options =
                            androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    LoginActivity.this,
                                    findViewById(R.id.logo_container),
                                    "logoTransition"); // must match android:transitionName in both XMLs

                    // Launch ChatActivity with the smooth transition
                    startActivity(intent, options.toBundle());

                } else {
                    // Wrong credentials — show a toast error message
                    Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
