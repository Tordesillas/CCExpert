package fr.polytech.ccexpert.model;

import com.codename1.ui.Image;

public class Hero {
    /*ANGEL, MARAUDER, HILL_GIANT, ENGINEER, FROST_WITCH, DRYAD, MARKSMAN, ALCHEMIST,    //Ordinary
    EXECUTIONER, ASSASSIN, WEREWOLF, CYCLOPS, SHAMAN, PAIN_DA, SERPENT_QUEEN, ICE_DEMON, TRITON,     //Elite
    PALADIN, CHAMPION, SUCCUBUS, DRUID, NINJA, THUNDER_GOD, ATLANTICORE, GRIZZLY_REAPER, IMMORTEP, SIREN, PHANTOM_KING,     //Shards
    MOLTANICA, ARTICA, DEMOGORGON, SKELETICA,      //Events
    SPIRIT_MAGE, MINOTAUR_CHIEFTAIN, DESTROYER,     //Purchases
    PUMPKIN_DUKE, SNOWZILLA, CUPID, ARIES, VLAD_DRACULA, ORKSBANE, SANTA_BOOM, PIXIE, DEATH_KNIGHT, WARLOCK,
    TREANTAUR, HARPY_QUEEN, SKULL_KNIGHT, DREAD_DRAKE, GHOULEM, CANDY_KANE, VALENTINA, BEAST_TAMER, LADY_LEO, GRIMFIEND,
    DRACAX, MEDUSA, TRIXIE_TREAT, REVENANT, LIL_NICK, MICHAEL, HEARTBREAKER, ANUBIS, RONIN, GUNSLINGER, ROCKNO, MECHTESSA; */    //Gem rolls

    private String name;
    private Image picture;
    private Crest crest;
    //talent, vie, vitesse d'attaque, attaque, opt(3ème talent), gravure, attaque équipement, esquive, précision, critique
    // dégats critiques, résistance critique

    public Hero(String name, Crest crest) {
        this.name = name;
        this.picture = picture;
        this.crest = crest;
    }

    public Hero(String name) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public Image getPicture() {
        return picture;
    }

    public Crest getCrest() {
        return crest;
    }
}
