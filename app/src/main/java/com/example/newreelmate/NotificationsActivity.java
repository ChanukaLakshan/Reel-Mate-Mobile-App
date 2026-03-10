package com.example.newreelmate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        BottomNavHelper.setup(this, R.id.nav_notifications);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavHelper.setup(this, R.id.nav_notifications);
    }
}
