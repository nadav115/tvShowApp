package com.example.tvshowapp;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Movie;
import android.location.GnssAntennaInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class TvShowAdapater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private ArrayList<TvShow> tvShows = new ArrayList<>();
    // private TvShow TvShowItemClickListener;

    public TvShowAdapater(Activity activity, ArrayList<TvShow> tvShows) {
        this.activity = activity;
        this.tvShows = tvShows;
    }

    public TvShowAdapater setTvShowItemClickListener(TvShowItemClickListener TvShowItemClickListener) {
        this.TvShowItemClickListener = TvShowItemClickListener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new TvShowViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TvShowViewHolder tvShowViewHolder = (TvShowViewHolder) holder;
        TvShow tvShow = getItem(position);
        Log.d("pttt", "position= " + position);

        tvShowViewHolder.title.setText(tvShow.getTitle());
        tvShowViewHolder.genre.setText(tvShow.getGenre());



//        Glide
//                .with(activity)
//                .load(tvShow.getImage())
//                .into(movieViewHolder.movie_IMG_image);
//
//        if (movie.isFavorite()) {
//            movieViewHolder.movie_IMG_favorite.setImageResource(R.drawable.ic_heart_filled);
//        } else  {
//            movieViewHolder.movie_IMG_favorite.setImageResource(R.drawable.ic_heart_empty);
//        }
//
//        float rating = movie.getRating();
//        rating /= 20;
//        movieViewHolder.movie_RTNG_stars.setRating(rating);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    private TvShow getItem(int position) {
        return tvShows.get(position);
    }

    public interface MovieItemClickListener {
        void movieItemClicked(Movie movie, int position);
        void favoriteClicked(Movie movie, int position);
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {

        //public AppCompatImageView movie_IMG_image;
        //public AppCompatImageView movie_IMG_favorite;
        public TextView title;
        public TextView genre;
//        public MaterialTextView movie_LBL_actors;
//        public MaterialTextView movie_LBL_duration;
//        public AppCompatRatingBar movie_RTNG_stars;

        public TvShowViewHolder(final View itemView) {
            super(itemView);
            this.title= itemView.findViewById(R.id.tv_show_title);
            this.genre= itemView.findViewById(R.id.tv_genre_name);
//            this.movie_IMG_image = itemView.findViewById(R.id.movie_IMG_image);
//            this.movie_IMG_favorite = itemView.findViewById(R.id.movie_IMG_favorite);
//            this.movie_LBL_title = itemView.findViewById(R.id.movie_LBL_title);
//            this.movie_LBL_actors = itemView.findViewById(R.id.movie_LBL_actors);
//            this.movie_LBL_duration = itemView.findViewById(R.id.movie_LBL_duration);
//            this.movie_RTNG_stars = itemView.findViewById(R.id.movie_RTNG_stars);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    movieItemClickListener.movieItemClicked(getItem(getAdapterPosition()), getAdapterPosition());
//                }
//            });
//
//            movie_IMG_favorite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    movieItemClickListener.favoriteClicked(getItem(getAdapterPosition()), getAdapterPosition());
//                }
//            });
        }
    }
}
