package fr.polytech.ccexpert.model;

import java.util.HashMap;
import java.util.Map;

public class Sets {
    private Map<Integer, Hero> heroesIds;
    private Map<Integer, Crest> crestsIds;
    private Map<String, Hero> heroesNames;
    private Map<String, Crest> crestsNames;

    public Sets() {
        heroesIds = new HashMap<>();
        crestsIds = new HashMap<>();
        heroesNames = new HashMap<>();
        crestsNames = new HashMap<>();
    }

    public void addHero(Hero hero, int id) {
        heroesIds.put(id, hero);
        heroesNames.put(hero.getName(), hero);
    }

    public void addCrest(Crest crest, int id) {
        crestsIds.put(id, crest);
        crestsNames.put(crest.getName(), crest);
    }

    public Hero getHero(int id) {
        return heroesIds.get(id);
    }

    public Crest getCrest(int id) {
        return crestsIds.get(id);
    }

    public Hero getHero(String name) {
        return heroesNames.get(name);
    }

    public Crest getCrest(String name) {
        return crestsNames.get(name);
    }
}
