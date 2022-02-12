package com.example.tvshowapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity_Menu extends AppCompatActivity {
    private ImageButton favorites , search , addPhoto;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText tvShowInput;
    private ArrayList<TvShow> tvShowsList , searchedTvShows;

    TvShowAdapter tvShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        favorites = findViewById(R.id.favorite_btn);
        search = findViewById(R.id.search_btn);
        tvShowInput = findViewById(R.id.search_input);
        addPhoto = findViewById(R.id.addPhoto_btn);

        tvShowsList = new ArrayList<>();
        RecyclerView rv;

        rv = findViewById(R.id.rv_tvShows);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference questionsRef = rootRef.collection("tv_shows");
        questionsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        TvShow qst = document.toObject(TvShow.class);

                        // Add all to list
                        tvShowsList.add(qst);
                    }
                    tvShowAdapter = new TvShowAdapter(Activity_Menu.this,tvShowsList);
                    rv.setAdapter(tvShowAdapter);

                    tvShowAdapter.setTvShowItemClickListener(new TvShowAdapter.TvShowItemClickListener() {
                            @Override
                            public void TvShowItemClicked(TvShow tvShow, int position) {

                                Intent intent = new Intent(Activity_Menu.this, Activity_Item_Page.class);
                                intent.putExtra("tvShow_title", tvShow.getTitle());
                                intent.putExtra("from", "menu");
                                startActivity(intent);
                            }

                            @Override
                            public void favoriteClicked(TvShow tvShow, int position) {
                                tvShow.setFavorite(!tvShow.isFavorite());
                                DocumentReference favChange = db.collection("tv_shows").document(tvShow.getTitle());

                                favChange.update("favorite", tvShow.isFavorite());
                                rv.getAdapter().notifyItemChanged(position);
                            }
                    });
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          String tvShow_title = tvShowInput.getText().toString().toLowerCase();
                                          searchedTvShows = new ArrayList<TvShow>();
                                          if (tvShow_title.equals("")) {
                                              searchedTvShows = tvShowsList;
                                          } else {
                                              for (TvShow tvShow : tvShowsList) {
                                                  if (tvShow.getTitle().toLowerCase().contains(tvShow_title)) {
                                                      searchedTvShows.add(tvShow);
                                                  }
                                              }
                                          }

                                          tvShowAdapter = new TvShowAdapter(Activity_Menu.this,searchedTvShows);
                                          rv.setAdapter(tvShowAdapter);

                                         tvShowAdapter.setTvShowItemClickListener(new TvShowAdapter.TvShowItemClickListener() {
                            @Override
                            public void TvShowItemClicked(TvShow tvShow, int position) {

                                Intent intent = new Intent(Activity_Menu.this, Activity_Item_Page.class);
                                intent.putExtra("tvShow_title", tvShow.getTitle());
                                intent.putExtra("from", "menu");
                                startActivity(intent);
                            }

                            @Override
                            public void favoriteClicked(TvShow tvShow, int position) {
                                tvShow.setFavorite(!tvShow.isFavorite());
                                DocumentReference favChange = db.collection("tv_shows").document(tvShow.getTitle());
                                favChange.update("favorite", tvShow.isFavorite());
                                rv.getAdapter().notifyItemChanged(position);
                            }
                    });

                                      }
                                  });

        addPhoto.setOnClickListener(v -> {
            startActivity(new Intent(this, Activity_Photo.class));
        });


        favorites.setOnClickListener(v -> {
            startActivity(new Intent(this, Activity_Favorites.class));
        });


    }



}



