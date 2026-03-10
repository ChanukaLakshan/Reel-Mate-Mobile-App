package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenreSelectionActivity extends AppCompatActivity {

    // Predefined genre name -> TMDB genre ID mapping
    private static final Map<String, Integer> GENRE_MAP = new LinkedHashMap<String, Integer>() {{
        put("Action",       28);
        put("Comedy",       35);
        put("Drama",        18);
        put("Horror",       27);
        put("Sci-Fi",       878);
        put("Thriller",     53);
        put("Romance",      10749);
        put("Animation",    16);
        put("Documentary",  99);
        put("Adventure",    12);
        put("Fantasy",      14);
        put("Crime",        80);
        put("Mystery",      9648);
        put("Music",        10402);
        put("History",      36);
        put("War",          10752);
        put("Western",      37);
        put("Family",       10751);
    }};

    private static final int MIN_SELECTIONS = 3;

    private ChipGroup genreChipGroup;
    private Button continueButton;
    private TextView selectionCountTextView;

    private final List<String> selectedGenreNames = new ArrayList<>();
    private final List<Integer> selectedGenreIds = new ArrayList<>();

    private ReelMateRepository repository;
    private SessionManager sessionManager;

    /** Pass this extra to indicate we're coming from signup flow (new user id) */
    public static final String EXTRA_USER_ID = "extra_user_id";
    /** Pass this extra if user already has genres (edit flow from settings) */
    public static final String EXTRA_EXISTING_GENRES = "extra_existing_genres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_selection);

        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);

        genreChipGroup = findViewById(R.id.genreChipGroup);
        continueButton = findViewById(R.id.continueButton);
        selectionCountTextView = findViewById(R.id.selectionCountTextView);

        buildGenreChips();
        restoreExistingSelections();

        continueButton.setOnClickListener(v -> saveAndContinue());

        TextView skipTextView = findViewById(R.id.skipTextView);
        skipTextView.setOnClickListener(v -> goToHome());
    }

    private void buildGenreChips() {
        for (Map.Entry<String, Integer> entry : GENRE_MAP.entrySet()) {
            Chip chip = new Chip(this);
            chip.setText(entry.getKey());
            chip.setCheckable(true);
            chip.setChecked(false);

            // Styling
            chip.setChipBackgroundColor(ContextCompat.getColorStateList(this, R.color.chip_bg_selector));
            chip.setTextColor(ContextCompat.getColorStateList(this, R.color.chip_text_selector));
            chip.setChipStrokeColor(ContextCompat.getColorStateList(this, R.color.chip_stroke_selector));
            chip.setChipStrokeWidth(2f);
            chip.setTextSize(14f);
            chip.setEnsureMinTouchTargetSize(false);

            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                String name = buttonView.getText().toString();
                int genreId = GENRE_MAP.getOrDefault(name, 0);
                if (isChecked) {
                    if (!selectedGenreNames.contains(name)) selectedGenreNames.add(name);
                    if (!selectedGenreIds.contains(genreId)) selectedGenreIds.add(genreId);
                } else {
                    selectedGenreNames.remove(name);
                    selectedGenreIds.remove(Integer.valueOf(genreId));
                }
                updateSelectionUI();
            });

            genreChipGroup.addView(chip);
        }
    }

    private void restoreExistingSelections() {
        ArrayList<String> existing = getIntent().getStringArrayListExtra(EXTRA_EXISTING_GENRES);
        if (existing != null && !existing.isEmpty()) {
            for (int i = 0; i < genreChipGroup.getChildCount(); i++) {
                View child = genreChipGroup.getChildAt(i);
                if (child instanceof Chip) {
                    Chip chip = (Chip) child;
                    if (existing.contains(chip.getText().toString())) {
                        chip.setChecked(true);
                    }
                }
            }
        }
    }

    private void updateSelectionUI() {
        int count = selectedGenreNames.size();
        selectionCountTextView.setText(count + " selected (min " + MIN_SELECTIONS + ")");

        boolean canContinue = count >= MIN_SELECTIONS;
        continueButton.setEnabled(canContinue);
        continueButton.setAlpha(canContinue ? 1f : 0.5f);
    }

    private void saveAndContinue() {
        if (selectedGenreNames.size() < MIN_SELECTIONS) {
            Toast.makeText(this, "Please select at least " + MIN_SELECTIONS + " genres", Toast.LENGTH_SHORT).show();
            return;
        }

        int userId = getUserId();
        if (userId == -1) {
            goToHome();
            return;
        }

        continueButton.setEnabled(false);
        // Save genre names (readable) to Room DB
        repository.saveFavoriteGenres(userId, selectedGenreNames, success -> {
            continueButton.setEnabled(true);
            if (success) {
                goToHome();
            } else {
                Toast.makeText(this, "Failed to save genres. Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getUserId() {
        int id = getIntent().getIntExtra(EXTRA_USER_ID, -1);
        if (id == -1) id = sessionManager.getUserId();
        return id;
    }

    private void goToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    /** Converts genre names (stored in DB) back to TMDB IDs */
    public static List<Integer> getGenreIdsForNames(List<String> names) {
        List<Integer> ids = new ArrayList<>();
        for (String name : names) {
            if (GENRE_MAP.containsKey(name)) {
                ids.add(GENRE_MAP.get(name));
            }
        }
        return ids;
    }
}


