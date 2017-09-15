package fr.polytech.ccexpert.model;

import com.codename1.io.URL;

import java.net.URISyntaxException;

public class Dungeon {
    private URL urlYoutube;
    private int door;
    private int base;
    private int[] heroesIds;

    Dungeon(String urlYoutube, int door, int base, int compo1, int compo2, int compo3, int compo4, int compo5, int compo6) {
        try {
            this.urlYoutube = new URL(urlYoutube);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.door = door;
        this.base = base;
        heroesIds = new int[]{compo1, compo2, compo3, compo4, compo5, compo6};
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

    public int[] getHeroesIds() {
        return heroesIds;
    }
}
