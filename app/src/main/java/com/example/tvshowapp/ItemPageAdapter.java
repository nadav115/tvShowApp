package com.example.tvshowapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;

    public class ItemPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Activity activity;
        private TvShow tvShow = new TvShow();


        public ItemPageAdapter(Activity activity, TvShow tvShow) {
            this.activity = activity;
            this.tvShow = tvShow;
        }

        @Override
        public RecyclerView.ViewHolder
        onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_page, viewGroup, false);
            return new TvShowViewHolder(view);
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            TvShowViewHolder tvShowViewHolder = (TvShowViewHolder) holder;

            tvShowViewHolder.title.setText(tvShow.getTitle());
            tvShowViewHolder.genre.setText(tvShow.getGenre());
            tvShowViewHolder.release_date.setText(tvShow.getReleaseDate());
            tvShowViewHolder.seasons.setText(tvShow.getSeasons());
            tvShowViewHolder.episodes.setText(tvShow.getEpisodes());

            Glide.with(activity).load(tvShow.getUrl()).into(tvShowViewHolder.TvShow_image);
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        private TvShow getItem(int position) {
            return tvShow;
        }


        public class TvShowViewHolder extends RecyclerView.ViewHolder {

            public MaterialTextView title;
            public MaterialTextView genre;
            public MaterialTextView seasons;
            public MaterialTextView episodes;
            public MaterialTextView release_date;
            public AppCompatImageView TvShow_image;


            public TvShowViewHolder(final View itemView) {
                super(itemView);
                this.title= itemView.findViewById(R.id.tv_show_title);
                this.genre= itemView.findViewById(R.id.tv_genre_name);
                this.seasons= itemView.findViewById(R.id.tv_seasons);
                this.episodes= itemView.findViewById(R.id.tv_episodes);
                this.release_date= itemView.findViewById(R.id.tv_release_date);
                this.TvShow_image = itemView.findViewById(R.id.TvShow_image);
            }
        }
    }


