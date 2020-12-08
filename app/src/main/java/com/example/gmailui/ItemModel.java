package com.example.gmailui;

import android.graphics.Color;

public class ItemModel {
    private Color backgroundColor;
    private String myHexColor;
    private String name;
    private String title;
    private String content;
    private String time;
    private boolean favorite;

    public ItemModel() {
        favorite = false;
    }

    public ItemModel(String name, String title, String content, String time, String myHexColor) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.time = time;
        this.favorite = false;
        this.myHexColor = myHexColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getMyHexColor() {
        return myHexColor;
    }

    public void setMyHexColor(String color) {
        this.myHexColor = color;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}