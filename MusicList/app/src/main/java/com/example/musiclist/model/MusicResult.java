package com.example.musiclist.model;

import java.util.List;

public class MusicResult {
    public int resultCount;
    public List<MusicModel> results;

    public int getResultCount() {
        return resultCount;
    }

    public List<MusicModel> getResults() {
        return results;
    }
}
