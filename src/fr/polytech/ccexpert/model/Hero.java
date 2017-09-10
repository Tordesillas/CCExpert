package fr.polytech.ccexpert.model;

import com.codename1.ui.Image;

public class Hero {
    private String name;
    private Image picture;

    private int attackSpeed;
    private int attack;
    private int attackByLvl;
    private int healthPoints;
    private int healthByLvl;
    private int speed;
    private int speedByLvl;
    private int criticalHit;
    private int criticalDamage;
    private int criticalResist;
    private int accuracy;
    private int dodge;
    private int range;
    private int inscription;
    private String talent;
    private String equipmentTalent;

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

    public int getAttackSpeed() {
        return attackSpeed;
    }
}
