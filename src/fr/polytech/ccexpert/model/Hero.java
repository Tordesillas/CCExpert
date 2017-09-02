package fr.polytech.ccexpert.model;

import com.codename1.ui.Image;

public class Hero {
    private String name;
    private Image picture;

    public Hero(String name, Image picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public Image getPicture() {
        return picture;
    }
}
