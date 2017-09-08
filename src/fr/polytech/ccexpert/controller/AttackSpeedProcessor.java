package fr.polytech.ccexpert.controller;

import com.codename1.ui.Container;

public class AttackSpeedProcessor {
    public Container printAttackSpeedAmount(String hero, int ff, boolean blitz, boolean fureur, int duke, int hits, int mika, int destr) {
        Container c = new Container();
        System.out.println(hero+" "+ff+" "+blitz+" "+fureur+" "+duke+" "+hits+" "+mika+" "+destr);
        return c;
    }
}
