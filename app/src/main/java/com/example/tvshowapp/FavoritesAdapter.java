package com.example.tvshowapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private ArrayList<TvShow> tvShows = new ArrayList<>();
    private TvShowItemClickListener tvShowItemClickListener;


    public FavoritesAdapter(Activity activity, ArrayList<TvShow> tvShows) {
        this.activity = activity;
        this.tvShows = tvShows;
    }


    public FavoritesAdapter setTvShowItemClickListener(TvShowItemClickListener tvShowItemClickListener) {
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
        TvShowViewHolder tvShowViewHolder = (TvShowViewHolder) holder;
        TvShow tvShow = getItem(position);


            tvShowViewHolder.title.setText(tvShow.getTitle());
            tvShowViewHolder.genre.setText(tvShow.getGenre());
            Glide
                    .with(activity)
                    .load(tvShow.getUrl())
                    .into(tvShowViewHolder.TvShow_image);

            if (tvShow.isFavorite()) {
                tvShowViewHolder.tvShowFav.setImageResource(R.drawable.img_fav_logo);
            } else {
                tvShowViewHolder.tvShowFav.setImageResource(R.drawable.img_star_empty);
            }
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
        public MaterialTextView title;
        public MaterialTextView genre;
        public AppCompatImageView tvShowFav;
        public AppCompatImageView TvShow_image;

        public TvShowViewHolder(final View itemView) {
            super(itemView);
            this.title= itemView.findViewById(R.id.tv_show_title);
            this.genre= itemView.findViewById(R.id.tv_genre_name);
            this.tvShowFav = itemView.findViewById(R.id.btn_fav);
            this.TvShow_image = itemView.findViewById(R.id.TvShow_image);

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
