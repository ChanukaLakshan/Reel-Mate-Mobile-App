package com.example.newreelmate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "reelmate_settings";
    private static final String KEY_NOTIFICATIONS = "notifications_enabled";
    private static final String KEY_AUTO_PLAY = "auto_play_trailers";

    private SwitchCompat notificationsSwitch;
    private SwitchCompat autoPlaySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notificationsSwitch = findViewById(R.id.notificationsSwitch);
        autoPlaySwitch = findViewById(R.id.autoPlaySwitch);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        notificationsSwitch.setChecked(prefs.getBoolean(KEY_NOTIFICATIONS, true));
        autoPlaySwitch.setChecked(prefs.getBoolean(KEY_AUTO_PLAY, false));

        notificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
            prefs.edit().putBoolean(KEY_NOTIFICATIONS, isChecked).apply()
        );
        autoPlaySwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
            prefs.edit().putBoolean(KEY_AUTO_PLAY, isChecked).apply()
        );
    }
}

