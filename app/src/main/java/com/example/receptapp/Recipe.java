package com.example.receptapp;

import java.io.Serializable;

public class Recipe implements Serializable {
    private final String name;
    private final int quality;
    private final int difficulty;

    public Recipe(String name, int quality, int difficulty) {
        this.name = name;
        this.quality = quality;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public int getQuality() {
        return quality;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
