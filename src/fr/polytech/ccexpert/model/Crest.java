package fr.polytech.ccexpert.model;

import java.util.HashMap;

public enum Crest {
    BLADE_SHELL, FLAME_GUARD, SCORCH, SLOW_DOWN, SPRINT, STONE_SKIN, TENACITY,
    BULWARK, DEADLY_STIKE, HEAVY_BLOW, SCATTER, SELF_DESTRUCT, WAR_GOD, BERZERK,
    LIFE_DRAIN, REVITALIZE, REVIVE, PSYSHIELD;

    private int level;

    private static final HashMap<Crest, String> linkPictures = new HashMap<>();
    static {
        linkPictures.put(BLADE_SHELL, "unicorn.jpg");
        linkPictures.put(FLAME_GUARD, "unicorn.jpg");
        linkPictures.put(SCORCH, "unicorn.jpg");
        linkPictures.put(SLOW_DOWN, "unicorn.jpg");
        linkPictures.put(SPRINT, "unicorn.jpg");
        linkPictures.put(STONE_SKIN, "unicorn.jpg");
        linkPictures.put(TENACITY, "unicorn.jpg");
        linkPictures.put(BULWARK, "unicorn.jpg");
        linkPictures.put(DEADLY_STIKE, "unicorn.jpg");
        linkPictures.put(HEAVY_BLOW, "unicorn.jpg");
        linkPictures.put(SCATTER, "unicorn.jpg");
        linkPictures.put(SELF_DESTRUCT, "unicorn.jpg");
        linkPictures.put(WAR_GOD, "unicorn.jpg");
        linkPictures.put(BERZERK, "unicorn.jpg");
        linkPictures.put(LIFE_DRAIN, "unicorn.jpg");
        linkPictures.put(REVITALIZE, "unicorn.jpg");
        linkPictures.put(REVIVE, "unicorn.jpg");
        linkPictures.put(PSYSHIELD, "unicorn.jpg");
    }

    public String getPicture(Crest crest) {
        return linkPictures.get(crest);
    }

    public int getLevel() {
        return level;
    }
}
