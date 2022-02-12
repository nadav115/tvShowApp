package com.example.tvshowapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class Activity_Item_Page extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TvShow tvShow;
    private ImageButton btn_back;
    ItemPageAdapter itemPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);
        btn_back = findViewById(R.id.btn_back);
        String selectedTvShow = getIntent().getStringExtra("tvShow_title");


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
                        // Add all to list

                        if (selectedTvShow.compareTo(qst.getTitle()) == 0){
                            tvShow = qst;
                        }
                    }
                    itemPageAdapter = new ItemPageAdapter(Activity_Item_Page.this,tvShow);
                    rv.setAdapter(itemPageAdapter);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i ;
                if(getIntent().getStringExtra("from").equals("menu")){
                i = new Intent(Activity_Item_Page.this,Activity_Menu.class);
                }else {
                    i = new Intent(Activity_Item_Page.this,Activity_Favorites.class);
                }
                startActivity(i);
            }
        });
    }
}


