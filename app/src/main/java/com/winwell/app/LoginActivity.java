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
 * LoginActivity handles the user authentication process.
 * It provides a simple username/password interface and navigates to the main
 * ChatActivity upon success.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components using findViewById
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check hardcoded credentials for demonstration purposes
                if ("uritheteacher@gmail.com".equals(username) && "androidstudio".equals(password)) {
                    // Navigate to ChatActivity with a shared element transition for a smooth UI
                    // effect
                    // Navigate to ChatActivity with transition
                    Intent intent = new Intent(LoginActivity.this, ChatActivity.class);

                    androidx.core.app.ActivityOptionsCompat options = androidx.core.app.ActivityOptionsCompat
                            .makeSceneTransitionAnimation(
                                    LoginActivity.this,
                                    findViewById(R.id.logo_container),
                                    "logoTransition"); // "logoTransition" must match the android:transitionName in XML

                    startActivity(intent, options.toBundle());
                    // finish(); // Keep activity for transition to work properly, or delay finish
                } else {
                    Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
