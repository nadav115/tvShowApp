package com.example.tvshowapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Activity_Menu extends AppCompatActivity {
    private ImageButton favorites , search;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<TvShow> tvShowsList;
    ImageView imgv;
    TvShowAdapter tvShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        favorites = findViewById(R.id.favorite_btn);
        search = findViewById(R.id.search_btn);
        //sv = findViewById(R.id.sv_fragment);
        //imgv = findViewById(R.id.imgv);
        //Glide.with(this).load("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/screen-shot-2019-12-16-at-10-16-52-am-1576509427.png").into(imgv);
        tvShowsList = new ArrayList<>();
        RecyclerView rv;

        DatabaseReference databaseReference;
        ArrayList<TvShow> list = new ArrayList<>();

        rv = findViewById(R.id.rv_tvShows);
        rv.setHasFixedSize(true);
        //rv.setLayoutManager(new LinearLayoutManager(this));
        //db = FirebaseFirestore.getInstance();

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference questionsRef = rootRef.collection("tv_shows");
        questionsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        TvShow qst = document.toObject(TvShow.class);
                        Log.d("Tv Show title:", qst.getTitle());
                        Log.d("Tv Show title:", qst.getEpisodes());
                        Log.d("Tv Show title:", qst.getSeasons());



                        // Add all to your list
                        tvShowsList.add(qst);
                        Log.d("dsd:"," size is "+tvShowsList.size());

                    }
                    tvShowAdapter = new TvShowAdapter(Activity_Menu.this,tvShowsList);
                    rv.setAdapter(tvShowAdapter);
                }

            }
        });













        favorites.setOnClickListener(v -> {
            startActivity(new Intent(this, Activity_Favorites.class));
        });


    }



}


