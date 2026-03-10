package com.example.newreelmate;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Helper to set up the bottom navigation bar consistently across activities.
 */
public class BottomNavHelper {

    public static void setup(Activity activity, int selectedItemId) {
        BottomNavigationView bottomNav = activity.findViewById(R.id.bottomNavigationView);
        if (bottomNav == null) return;

        // Set active indicator color programmatically
        bottomNav.setItemActiveIndicatorColor(
                ColorStateList.valueOf(activity.getColor(R.color.background_tertiary)));

        // Suppress tap on already-selected tab (no re-launch)
        bottomNav.setOnItemReselectedListener(item -> { /* do nothing */ });

        // 2. Set the selected item AFTER the listener is attached,
        //    using post() so it runs after the current layout pass —
        //    this prevents the listener from being triggered by setSelectedItemId itself.
        bottomNav.post(() -> {
            // Temporarily remove listener so setSelectedItemId doesn't fire navigation
            bottomNav.setOnItemSelectedListener(null);
            bottomNav.setSelectedItemId(selectedItemId);

            // Now attach the real listener for user taps
            bottomNav.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    if (!(activity instanceof HomeActivity)) {
                        navigate(activity, HomeActivity.class);
                    }
                    return true;
                } else if (id == R.id.nav_lists) {
                    if (!(activity instanceof MyListsActivity)) {
                        navigate(activity, MyListsActivity.class);
                    }
                    return true;
                } else if (id == R.id.nav_notifications) {
                    if (!(activity instanceof NotificationsActivity)) {
                        navigate(activity, NotificationsActivity.class);
                    }
                    return true;
                } else if (id == R.id.nav_profile) {
                    if (!(activity instanceof ProfileActivity)) {
                        navigate(activity, ProfileActivity.class);
                    }
                    return true;
                }
                return false;
            });

            bottomNav.setOnItemReselectedListener(item -> { /* do nothing */ });
        });
    }

    private static void navigate(Activity from, Class<?> to) {
        Intent intent = new Intent(from, to);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        from.startActivity(intent);
        from.overridePendingTransition(0, 0);
    }
}
