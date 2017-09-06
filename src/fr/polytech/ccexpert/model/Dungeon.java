package fr.polytech.ccexpert.model;

import com.codename1.io.URL;

import java.util.ArrayList;

public class Dungeon {
    private URL urlYoutube;
    private int door;
    private int base;
    private ArrayList<Hero> heroes;
    private ArrayList<Crest> crests;

    public Dungeon(URL urlYoutube, int door, int base, ArrayList<Hero> heroes, ArrayList<Crest> crests) {
        this.urlYoutube = urlYoutube;
        this.door = door;
        this.base = base;
        this.heroes = heroes;
        this.crests = crests;
    }

    public URL getUrlYoutube() {
        return urlYoutube;
    }

    public int getDoor() {
        return door;
    }

    public int getBase() {
        return base;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public ArrayList<Crest> getCrests() {
        return crests;
    }
}
