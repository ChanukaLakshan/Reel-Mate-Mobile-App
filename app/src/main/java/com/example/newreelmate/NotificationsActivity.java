package com.example.newreelmate;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newreelmate.adapters.NotificationAdapter;
import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;

public class NotificationsActivity extends AppCompatActivity {

    private NotificationAdapter adapter;
    private ReelMateRepository repository;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        sessionManager = new SessionManager(this);
        repository     = new ReelMateRepository(this);

        RecyclerView recyclerView   = findViewById(R.id.notificationsRecyclerView);
        LinearLayout emptyState     = findViewById(R.id.emptyStateLayout);
        TextView markAllReadButton  = findViewById(R.id.markAllReadButton);

        adapter = new NotificationAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        int userId = sessionManager.getUserId();

        // Observe notifications LiveData
        repository.getNotifications(userId).observe(this, notifications -> {
            if (notifications == null || notifications.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                emptyState.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                emptyState.setVisibility(View.GONE);
                adapter.setNotifications(notifications);
            }
        });

        // Mark all as read when the screen opens
        repository.markAllNotificationsRead(userId);

        markAllReadButton.setOnClickListener(v ->
            repository.markAllNotificationsRead(userId)
        );

        BottomNavHelper.setup(this, R.id.nav_notifications);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Mark all read again when returning to the screen
        repository.markAllNotificationsRead(sessionManager.getUserId());
        BottomNavHelper.setup(this, R.id.nav_notifications);
    }
}
