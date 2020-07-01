package com.azapps.moviereviewapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.azapps.moviereviewapp.R;
import com.azapps.moviereviewapp.pojo.Results;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.net.URI;

import retrofit2.http.Url;

import static com.azapps.moviereviewapp.repository.Constant.BASE_URL;

public class MovieAdapter extends ListAdapter<Results, MovieViewHolder> {
    private Context context;

    public MovieAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Results> diffCallback = new DiffUtil.ItemCallback<Results>() {
        @Override
            public boolean areItemsTheSame(Results oldItem, Results newItem) {
                return oldItem.getId() == newItem.getId();
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
        Uri image_url =Uri.parse("https://image.tmdb.org/t/p/w500/"+currentMovie.getPoster_path());
        Glide.with(context).load(image_url).into(holder.movieImageView);
//        holder.movieImageView.setImageURI(Uri.parse());
        holder.releaseDateTV.setText(currentMovie.getRelease_date());
        holder.titleTV.setText(currentMovie.getTitle());
        holder.ratingTv.setText(String.valueOf(currentMovie.getVote_average()));
    }
}
