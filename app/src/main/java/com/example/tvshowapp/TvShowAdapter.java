package com.example.tvshowapp;

import android.app.Activity;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private ArrayList<TvShow> tvShows = new ArrayList<>();
    private TvShowItemClickListener tvShowItemClickListener;


    public TvShowAdapter(Activity activity, ArrayList<TvShow> tvShows) {
        this.activity = activity;
        this.tvShows = tvShows;
    }


    public TvShowAdapter setTvShowItemClickListener(TvShowItemClickListener tvShowItemClickListener) {
        this.tvShowItemClickListener = tvShowItemClickListener;
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
        TvShowAdapter.TvShowViewHolder tvShowViewHolder = (TvShowAdapter.TvShowViewHolder) holder;
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

    public interface TvShowItemClickListener {
        void TvShowItemClicked(TvShow tvShow, int position);
        void favoriteClicked(TvShow tvShow, int position);
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {

        //public AppCompatImageView movie_IMG_image;
        //public AppCompatImageView movie_IMG_favorite;
        public TextView title;
        public TextView genre;
        public ImageButton tvShowFav;
//        public MaterialTextView movie_LBL_actors;
//        public MaterialTextView movie_LBL_duration;
//        public AppCompatRatingBar movie_RTNG_stars;

        public TvShowViewHolder(final View itemView) {
            super(itemView);
            this.title= itemView.findViewById(R.id.tv_show_title);
            this.genre= itemView.findViewById(R.id.tv_genre_name);
            this.tvShowFav = itemView.findViewById(R.id.btn_fav);
//            this.movie_IMG_image = itemView.findViewById(R.id.movie_IMG_image);
//            this.movie_IMG_favorite = itemView.findViewById(R.id.movie_IMG_favorite);
//            this.movie_LBL_title = itemView.findViewById(R.id.movie_LBL_title);
//            this.movie_LBL_actors = itemView.findViewById(R.id.movie_LBL_actors);
//            this.movie_LBL_duration = itemView.findViewById(R.id.movie_LBL_duration);
//            this.movie_RTNG_stars = itemView.findViewById(R.id.movie_RTNG_stars);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvShowItemClickListener.TvShowItemClicked(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });

            tvShowFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvShowItemClickListener.favoriteClicked(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
