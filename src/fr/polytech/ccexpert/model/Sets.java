package fr.polytech.ccexpert.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sets {
    private Map<Integer, Hero> heroesIds;
    private Map<String, Hero> heroesNames;
    private Map<Integer, HeroFaculties> heroesFaculties;
    private List<Dungeon> dungeonSet;

    Sets() {
        heroesIds = new HashMap<>();
        heroesNames = new HashMap<>();
        heroesFaculties = new HashMap<>();
        dungeonSet = new ArrayList<>();
    }

    void addHero(Hero hero, int id) {
        heroesIds.put(id, hero);
        heroesNames.put(hero.getName(), hero);
    }

    void addHeroCompo(HeroFaculties compo, int id) {
        heroesFaculties.put(id, compo);
    }

    void addDungeon(Dungeon dungeon) {
        dungeonSet.add(dungeon);
    }

    public Hero getHero(int id) {
        return heroesIds.get(id);
    }

    public Hero getHero(String name) {
        return heroesNames.get(name);
    }

    public HeroFaculties getHeroFaculties(int id) {
        return heroesFaculties.get(id);
    }

    public List<Dungeon> getDungeonSet() {
        return dungeonSet;
    }
}
