package com.azapps.moviereviewapp.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azapps.moviereviewapp.R;
import com.azapps.moviereviewapp.adapter.MovieAdapter;
import com.azapps.moviereviewapp.adapter.OnMovieClickListener;
import com.azapps.moviereviewapp.pojo.Movie;
import com.azapps.moviereviewapp.pojo.Results;
import com.azapps.moviereviewapp.repository.Constant;
import com.azapps.moviereviewapp.repository.MovieApi;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements OnMovieClickListener{
    private RecyclerView recyclerView;
    private List<Results> dataResults;
    private MovieApi movieApi;
    private MovieAdapter adapter;
    private int pageNumber = 1;
    // test
    List<Results> movieList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);

        buildRetrofit();

        getResultsFromRetrofit(pageNumber);


    }

    private void addClickEvent() {
        adapter.setOnMovieClickListener(new OnMovieClickListener() {
            @Override
            public void onMovieClick(int position) {
                Toast.makeText(MainActivity.this, movieList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApi = retrofit.create(MovieApi.class);
    }

    private void getResultsFromRetrofit(int pageId) {
        Call<Movie> call = movieApi.getMovies(Constant.API_KEY, pageId);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                // the result returned correctly
                dataResults = movie.getResults();
                buildRecyclerView(dataResults);

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void buildRecyclerView(List<Results> movies) {
        movieList = movies;
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.hasFixedSize();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MovieAdapter(this,this);
        adapter.submitList(movieList);
        addClickEvent();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(int position) {

    }
}