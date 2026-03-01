package com.example.newreelmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newreelmate.adapters.MovieListAdapter;
import com.example.newreelmate.data.DataProvider;
import com.example.newreelmate.models.MovieList;

import java.util.ArrayList;
import java.util.List;

public class MyListsActivity extends AppCompatActivity {

    private RecyclerView listsRecyclerView;
    private MovieListAdapter listAdapter;
    private List<MovieList> lists;
    private ActivityResultLauncher<Intent> createListLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists);

        createListLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String name = result.getData().getStringExtra("LIST_NAME");
                        String description = result.getData().getStringExtra("LIST_DESCRIPTION");
                        if (name != null && !name.trim().isEmpty()) {
                            MovieList newList = new MovieList(
                                lists.size() + 1,
                                name.trim(),
                                description == null ? "" : description.trim(),
                                0,
                                "Just now",
                                new java.util.ArrayList<String>()
                            );
                            lists.add(0, newList);
                            listAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        );

        initializeViews();
        setupRecyclerView();
        loadLists();
    }

    private void initializeViews() {
        listsRecyclerView = findViewById(R.id.listsRecyclerView);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.createListButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createListLauncher.launch(new Intent(MyListsActivity.this, CreateListActivity.class));
            }
        });

        findViewById(R.id.createNewListButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createListLauncher.launch(new Intent(MyListsActivity.this, CreateListActivity.class));
            }
        });
    }

    private void setupRecyclerView() {
        lists = new ArrayList<>(DataProvider.getMovieLists());
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
                    lists.remove(movieList);
                    listAdapter.notifyDataSetChanged();
                    Toast.makeText(MyListsActivity.this, "List deleted", Toast.LENGTH_SHORT).show();
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

    private void loadLists() {
        // Lists are loaded in the adapter constructor
    }
}
