package com.shentuo.builditbigger.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyJoke {

    private String jokeContent;

    public String getJokeContent() {
        return jokeContent;
    }

    public void setJokeContent(String jokeContent) {
        this.jokeContent = jokeContent;
    }
}