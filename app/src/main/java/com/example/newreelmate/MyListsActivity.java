package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newreelmate.adapters.MovieListAdapter;
import com.example.newreelmate.database.ReelMateRepository;
import com.example.newreelmate.database.SessionManager;
import com.example.newreelmate.database.entities.MovieListEntity;
import com.example.newreelmate.models.MovieList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyListsActivity extends AppCompatActivity {

    private RecyclerView listsRecyclerView;
    private MovieListAdapter listAdapter;
    private List<MovieList> lists;
    private ActivityResultLauncher<Intent> createListLauncher;
    private ReelMateRepository repository;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists);

        repository = new ReelMateRepository(this);
        sessionManager = new SessionManager(this);

        createListLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String name = result.getData().getStringExtra("LIST_NAME");
                        String description = result.getData().getStringExtra("LIST_DESCRIPTION");
                        if (name != null && !name.trim().isEmpty()) {
                            int userId = sessionManager.getUserId();
                            repository.createMovieList(userId, name.trim(),
                                description == null ? "" : description.trim(),
                                id -> loadListsFromDb()
                            );
                        }
                    }
                }
            }
        );

        initializeViews();
        setupRecyclerView();
        loadListsFromDb();
        BottomNavHelper.setup(this, R.id.nav_lists);
    }

    private void initializeViews() {
        listsRecyclerView = findViewById(R.id.listsRecyclerView);


        View createListButton = findViewById(R.id.createListButton);
        if (createListButton != null) {
            createListButton.setOnClickListener(v ->
                createListLauncher.launch(new Intent(MyListsActivity.this, CreateListActivity.class))
            );
        }

        View createNewListButton = findViewById(R.id.createNewListButton);
        if (createNewListButton != null) {
            createNewListButton.setOnClickListener(v ->
                createListLauncher.launch(new Intent(MyListsActivity.this, CreateListActivity.class))
            );
        }
    }

    private void setupRecyclerView() {
        lists = new ArrayList<>();
        listAdapter = new MovieListAdapter(this, lists,
            new MovieListAdapter.OnListClickListener() {
                @Override
                public void onListClick(MovieList movieList) {
                    Intent intent = new Intent(MyListsActivity.this, ListDetailActivity.class);
                    intent.putExtra("LIST_ID", movieList.getId());
                    startActivity(intent);
                }

                @Override
                public void onDeleteClick(MovieList movieList) {
                    int userId = sessionManager.getUserId();
                    repository.deleteMovieList(movieList.getId(), userId, success -> {
                        loadListsFromDb();
                        Toast.makeText(MyListsActivity.this, "List deleted", Toast.LENGTH_SHORT).show();
                    });
                }

                @Override
                public void onShareClick(MovieList movieList) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ReelMate List");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my list: " + movieList.getName());
                    startActivity(Intent.createChooser(shareIntent, "Share list"));
                }
            });

        listsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listsRecyclerView.setAdapter(listAdapter);
    }

    private void loadListsFromDb() {
        int userId = sessionManager.getUserId();
        repository.getMovieLists(userId).observe(this, entityList -> {
            lists.clear();
            if (entityList != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());
                for (MovieListEntity entity : entityList) {
                    String dateStr = sdf.format(new Date(entity.createdAt));
                    MovieList ml = new MovieList(
                        entity.id,
                        entity.name,
                        entity.description,
                        0,
                        dateStr,
                        new ArrayList<>()
                    );
                    lists.add(ml);
                }
            }
            listAdapter.notifyDataSetChanged();
        });
    }
}
