package com.azapps.moviereviewapp.pojo;

import java.util.List;

public class Results {


    private String poster_path;


    private String title;

    private double vote_average;


    private String release_date;


    public Results(String poster_path, String title, double vote_average, String release_date) {
        this.poster_path = poster_path;
        this.title = title;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }


    public String getTitle() {
        return title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

}
