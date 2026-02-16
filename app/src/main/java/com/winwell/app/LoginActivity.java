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

/**
 * LoginActivity is the first screen the user sees when opening the app.
 * It shows a simple login form with email and password fields.
 * If the credentials are correct, the user moves to the ChatActivity.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display so the app content stretches under the system bars
        EdgeToEdge.enable(this);

        // Set the layout for this screen
        setContentView(R.layout.activity_login);

        // Handle the system bars (status bar, navigation bar) so content doesn't overlap them
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Connect the XML views to Java variables so we can interact with them
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);

        // When the user clicks the Sign In button, check the credentials
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text the user typed in the email and password fields
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check if the credentials match the hardcoded values (for demo purposes)
                if ("uritheteacher@gmail.com".equals(username) && "androidstudio".equals(password)) {
                    // Create an Intent to navigate from LoginActivity to ChatActivity
                    Intent intent = new Intent(LoginActivity.this, ChatActivity.class);

                    // Set up a shared element transition - the logo will animate smoothly
                    // from the login screen to the chat screen
                    androidx.core.app.ActivityOptionsCompat options = androidx.core.app.ActivityOptionsCompat
                            .makeSceneTransitionAnimation(
                                    LoginActivity.this,
                                    findViewById(R.id.logo_container),
                                    "logoTransition"); // This name must match android:transitionName in the XML

                    // Start the ChatActivity with the transition animation
                    startActivity(intent, options.toBundle());
                } else {
                    // If credentials are wrong, show an error message to the user
                    Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
