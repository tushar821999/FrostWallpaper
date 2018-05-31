package com.androidgits.frostwallpaper.model;

/**
 * Created by Lenovo on 5/29/2018.
 */

public class Wall {
    private String name;
    private String Small_Image;
    private String Big_Image;
    private int Id;

    public Wall(){

    }

    public Wall(String name, String small_Image, String big_Image , int Id) {
        this.name = name;
        Small_Image = small_Image;
        Big_Image = big_Image;
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmall_Image() {
        return Small_Image;
    }

    public void setSmall_Image(String small_Image) {
        Small_Image = small_Image;
    }

    public String getBig_Image() {
        return Big_Image;
    }

    public void setBig_Image(String big_Image) {
        Big_Image = big_Image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
