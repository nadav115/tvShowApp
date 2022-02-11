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

public class Activity_Item_Page extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TvShow tvShow;
    private String selectedTvShow;
    ItemPageAdapter itemPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        String selectedTvShow = getIntent().getStringExtra("tvShow_title");



        Log.d("dsd:","tv show selected : " + selectedTvShow);
        tvShow = new TvShow();
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
                        Log.d("dsd:"," BBBBBBBBBBBBB " + qst.getTitle());
                        // Add all to your list

                        if (selectedTvShow.compareTo(qst.getTitle()) == 0){
                            tvShow = qst;
                            Log.d("dsd:"," aaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                        }
                    }
                    itemPageAdapter = new ItemPageAdapter(Activity_Item_Page.this,tvShow);
                    rv.setAdapter(itemPageAdapter);


                }
            }
        });



    }



}


