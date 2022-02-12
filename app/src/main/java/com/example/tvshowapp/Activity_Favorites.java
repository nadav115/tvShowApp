package com.example.tvshowapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity_Favorites  extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<TvShow> tvShowsList;
    FavoritesAdapter favoritesAdapter;
    private ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        tvShowsList = new ArrayList<>();
        RecyclerView rv;

        btn_back = findViewById(R.id.btn_back);

        DatabaseReference databaseReference;
        ArrayList<TvShow> list = new ArrayList<>();

        rv = findViewById(R.id.rv_tvShows_fav);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //db = FirebaseFirestore.getInstance();

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference questionsRef = rootRef.collection("tv_shows");
        questionsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        TvShow qst = document.toObject(TvShow.class);

                        // Add all to your list
                            if (qst.isFavorite()){
                                tvShowsList.add(qst);
                            }


                        Log.d("dsd:", "name:"+ qst.getTitle()+" favorite:  "+qst.isFavorite());

                    }
                    favoritesAdapter = new FavoritesAdapter(Activity_Favorites.this,tvShowsList);
                    rv.setAdapter(favoritesAdapter);

                    favoritesAdapter.setTvShowItemClickListener(new FavoritesAdapter.TvShowItemClickListener() {
                        @Override
                        public void TvShowItemClicked(TvShow tvShow, int position) {
                            Intent intent = new Intent(Activity_Favorites.this, Activity_Item_Page.class);
                            intent.putExtra("tvShow_title", tvShow.getTitle());
                            intent.putExtra("from", "favorite");
                            startActivity(intent);
                        }

                        @Override
                        public void favoriteClicked(TvShow tvShow, int position) {
                            tvShow.setFavorite(!tvShow.isFavorite());
                            DocumentReference favChange = db.collection("tv_shows").document(tvShow.getTitle());
                            favChange.update("favorite", tvShow.isFavorite());
                            //Toast.makeText(Activity_Menu.this, tvShow.getTitle() + "\n" + tvShow.getTitle(), Toast.LENGTH_SHORT).show();
                            rv.getAdapter().notifyItemChanged(position);
                        }
                    });
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(Activity_Favorites.this,Activity_Menu.class);
                startActivity(i);
            }
        });

    }
}
