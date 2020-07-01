package com.azapps.moviereviewapp.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azapps.moviereviewapp.R;
import com.azapps.moviereviewapp.pojo.Movie;
import com.azapps.moviereviewapp.pojo.Results;
import com.azapps.moviereviewapp.repository.Constant;
import com.azapps.moviereviewapp.repository.MovieApi;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Results> dataResults;
    private MovieApi movieApi;
    private int pageNumber = 1;
    // test
    List<Results> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApi = retrofit.create(MovieApi.class);

        getResultsFromRetrofit(pageNumber);


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

        //TODO: PUT YOUR ADAPTER CODE AND BUILD THE RECYCLER VIEW
//        list.addAll(movies);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.hasFixedSize();
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setItemAnimator(new SlideInUpAnimator());
//        listener = new EndlessRecyclerViewScrollListener(manager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                pageNumber++;
//                getResultsFromRetrofit(pageNumber);
//            }
//        };
//        recyclerView.addOnScrollListener(listener);
//        adapter = new MovieAdapter(this);
//        adapter.submitList(list);
//        recyclerView.setAdapter(adapter);
    }
}