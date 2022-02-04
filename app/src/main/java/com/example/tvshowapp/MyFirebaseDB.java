package com.example.tvshowapp;
import android.util.Log;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class MyFirebaseDB {

    public interface CallBack_String {
        void dataReady(String value);
    }

    public interface CallBack_Shows {
        void dataReady(ArrayList<tvShow> shows);
    }

    public static void getAllShows(CallBack_Shows callBack_shows) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("shows");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<tvShow> shows = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    try {
                        tvShow c = child.getValue(tvShow.class);
                        shows.add(c);
                    } catch (Exception ex) {}

                }
                if (callBack_shows != null) {
                    callBack_shows.dataReady(shows);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void gettvShowByName(String title, CallBack_String callBack_string) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("shows");
        myRef.child(title).child("title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              //  Log.d("pttt", "A Thread: " + Thread.currentThread().getName());
                String name = snapshot.getValue().toString();
                if (callBack_string != null) {
                    callBack_string.dataReady(name);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

