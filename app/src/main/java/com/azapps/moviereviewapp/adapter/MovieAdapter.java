package com.azapps.moviereviewapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azapps.moviereviewapp.R;
import com.azapps.moviereviewapp.pojo.Results;

import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private LayoutInflater mInflater;
    private List<Results> results;

    public MovieAdapter(Context context, List<Results> results) {
        mInflater = LayoutInflater.from(context);
        this.results = results;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movie_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Results currentResult = results.get(position);
        holder.title.setText(currentResult.getTitle());
        holder.rating.setText(String.valueOf(currentResult.getVote_average()));
        holder.releaseDate.setText(currentResult.getRelease_date());
        holder.imageView.setImageURI(Uri.parse(currentResult.getPoster_path()));

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title, rating, releaseDate;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_img_id);
            title = itemView.findViewById(R.id.movie_title);
            rating = itemView.findViewById(R.id.rating_number);
            releaseDate = itemView.findViewById(R.id.release_date);
        }
    }
}
