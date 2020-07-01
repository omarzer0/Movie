package com.azapps.moviereviewapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azapps.moviereviewapp.R;

class MovieViewHolder extends RecyclerView.ViewHolder implements OnMovieClickListener {
    ImageView movieImageView;
    TextView titleTV, releaseDateTV, ratingTv;
    OnMovieClickListener onMovieClickListener;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImageView = itemView.findViewById(R.id.movie_img_id);
        titleTV = itemView.findViewById(R.id.movie_title);
        releaseDateTV = itemView.findViewById(R.id.release_date);
        ratingTv = itemView.findViewById(R.id.rating_number);
    }

    @Override
    public void onMovieClick(int position) {
        onMovieClickListener.onMovieClick(getAdapterPosition());
    }
}
