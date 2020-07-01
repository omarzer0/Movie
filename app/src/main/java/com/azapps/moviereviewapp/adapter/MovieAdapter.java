package com.azapps.moviereviewapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.azapps.moviereviewapp.R;
import com.azapps.moviereviewapp.pojo.Results;
import com.bumptech.glide.Glide;

public class MovieAdapter extends ListAdapter<Results, MovieAdapter.MovieViewHolder> {
    private Context context;
    private OnMovieClickListener listener;

    public MovieAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Results> diffCallback = new DiffUtil.ItemCallback<Results>() {
        @Override
        public boolean areItemsTheSame(Results oldItem, Results newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(Results oldItem, Results newItem) {
            return (oldItem.getTitle().equals(newItem.getTitle()));
        }
    };

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Results currentMovie = getItem(position);
        Uri image_url = Uri.parse("https://image.tmdb.org/t/p/w500/" + currentMovie.getPoster_path());
        Glide.with(context).load(image_url).into(holder.movieImageView);
//        holder.movieImageView.setImageURI(Uri.parse());
        holder.releaseDateTV.setText(currentMovie.getRelease_date());
        holder.titleTV.setText(currentMovie.getTitle());
        holder.ratingTv.setText(String.valueOf(currentMovie.getVote_average()));
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImageView;
        TextView titleTV, releaseDateTV, ratingTv;
        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            movieImageView = itemView.findViewById(R.id.movie_img_id);
            titleTV = itemView.findViewById(R.id.movie_title);
            releaseDateTV = itemView.findViewById(R.id.release_date);
            ratingTv = itemView.findViewById(R.id.rating_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMovieClick(getItem(getAdapterPosition()));
                }
            });
        }

    }
    public interface OnMovieClickListener {
        void onMovieClick(Results result);
    }

    public void setOnMovieClickListener(OnMovieClickListener listener) {
        this.listener = listener;
    }

}
