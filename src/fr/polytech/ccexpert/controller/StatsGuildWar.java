package fr.polytech.ccexpert.controller;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class StatsGuildWar {
    private int power;
    private int score;
    private int averagePower;

    public StatsGuildWar() {

    }

    public Container printStats(int power, int score) {
        this.power = power;
        this.score = score;

        compute();

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.addComponent(new Label("Puissance moyenne attaquée : " + averagePower +" 000"));
        return c;
    }

    private void compute() {
        double scoreOnPower = (power == 0) ? 0 : score / (double)power;
        averagePower = (scoreOnPower < 0.005) ? 0 : Math.round((float)(0.14*(score-50-2.5*Math.sqrt(power))));
    }
}
