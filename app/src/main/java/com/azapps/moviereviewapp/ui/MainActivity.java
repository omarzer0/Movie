package com.azapps.moviereviewapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azapps.moviereviewapp.R;
import com.azapps.moviereviewapp.adapter.MovieAdapter;
import com.azapps.moviereviewapp.repository.Constant;
import com.azapps.moviereviewapp.repository.MovieApi;
import com.azapps.moviereviewapp.pojo.Movie;
import com.azapps.moviereviewapp.pojo.Results;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Results> dataResults;
    private MovieApi movieApi;
    private MovieAdapter adapter;
    //for testing
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =findViewById(R.id.tv_haha);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        movieApi = retrofit.create(MovieApi.class);
        Call<Movie> call = movieApi.getMovies(Constant.API_KEY,1);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                // the result returned correctly
                dataResults = movie.getResults();

                for (Results result : dataResults){
                    String s= "";
                    s+= result.getTitle()+"\n";
                    s+= result.getPoster_path()+"\n";
                    s+= result.getVote_average()+"\n";
                    s+= result.getRelease_date()+"\n"+"\n"+"\n";
                    textView.append(s);
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });



//        dataResults = new ArrayList<>();
//        dataResults.add(new Results(String.valueOf(R.drawable.a), "title",7.0,"12-12"));
//        dataResults.add(new Results(String.valueOf(R.drawable.a), "title",7.0,"12-12"));
//        dataResults.add(new Results(String.valueOf(R.drawable.a), "title",7.0,"12-12"));
//        dataResults.add(new Results(String.valueOf(R.drawable.a), "title",7.0,"12-12"));
//        dataResults.add(new Results(String.valueOf(R.drawable.a), "title",7.0,"12-12"));

        buildRecyclerView();

//        RatingBar ratingBar = findViewById(R.id.rating);
//        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
//        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
//        ratingBar.setRating(1f);

    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(this,dataResults);
        recyclerView.setAdapter(adapter);
    }
}