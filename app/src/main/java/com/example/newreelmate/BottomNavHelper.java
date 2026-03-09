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

        // Set the currently selected tab
        bottomNav.setSelectedItemId(selectedItemId);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                if (!(activity instanceof HomeActivity)) {
                    Intent intent = new Intent(activity, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                }
                return true;
            } else if (id == R.id.nav_lists) {
                if (!(activity instanceof MyListsActivity)) {
                    Intent intent = new Intent(activity, MyListsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                }
                return true;
            } else if (id == R.id.nav_notifications) {
                if (!(activity instanceof NotificationsActivity)) {
                    Intent intent = new Intent(activity, NotificationsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                }
                return true;
            } else if (id == R.id.nav_profile) {
                if (!(activity instanceof ProfileActivity)) {
                    Intent intent = new Intent(activity, ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                }
                return true;
            }
            return false;
        });
    }
}
