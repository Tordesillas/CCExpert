package fr.polytech.ccexpert.controller;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Container;
import fr.polytech.ccexpert.model.Hero;

public class AttackSpeedProcessor {
    private L10NManager l10n;
    private double[] skill = {1, 1.1, 1.15, 1.2, 1.25, 1.3, 1.35, 1.4, 1.45, 1.5, 1.55};
    private double[] skillMichael = {1, 1.18, 1.22, 1.26, 1.3, 1.34, 1.38, 1.43, 1.48, 1.54, 1.6};
    private double[] ffStats = {1, 1.1, 1.15, 1.2, 1.25, 1.3, 1.4, 1.5, 1.7};
    private double[] furyStats = {1, 1.1, 1.14, 1.19, 1.24, 1.3};

    public AttackSpeedProcessor() {
        l10n = L10NManager.getInstance();
    }

    public Container printAttackSpeedAmount(Hero hero, int ff, boolean blitz, int fury, int duke, int hits, int mika, int destroyer) {
        Container c = new Container();
        int basic = (hero == null) ? 1000 : hero.getAttackSpeed();
        double artifact = (blitz) ? 1.3 : 1;
        double res = basic / (Math.pow(skill[duke], hits) * skill[destroyer] * skillMichael[mika] * ffStats[ff] * artifact * furyStats[fury]);
        c.addComponent(new SpanLabel("Nouvelle vitesse d'attaque : " + l10n.format(res)));
        return c;
    }
}
