package com.example.aravind_pt1748.recyclerviewapp202;

/**
 * Created by aravind-pt1748 on 26/03/18.
 */

public class Game {

    private String title;
    private String genre;
    private String releaseYear;

    public Game(){

    }

    public Game(String title,String genre,String releaseYear){
        this.title=title;
        this.genre=genre;
        this.releaseYear=releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return releaseYear;
    }

    public void setYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
