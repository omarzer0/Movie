package com.azapps.moviereviewapp.pojo;

import java.util.List;

public class Results {


    private Integer vote_count;
    private boolean video;

    //TODO: 4-this is for poster (ImageView)
    private String poster_path;
    private Integer id;

    private boolean adult;

    private String backdrop_path;

    private String original_language;

    private String original_title;

    private List<Integer> genre_ids;

    //TODO: 1-this is for title TextView
    private String title;

    //TODO: 2-this is for AverageTextView
    private String vote_average;

    private String overview;

    //TODO: 3-This is for releaseData TextView
    private String release_date;


    public Integer getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500/"+poster_path;
    }

    public Integer getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public String getVote_average() {
        return String.valueOf(vote_average);
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }


}
