package fr.polytech.ccexpert.controller;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

import java.text.NumberFormat;

public class ProcessorShards {
    private static final int[] EXP_BY_LEVEL = {0, 1000, 3000, 10000, 30000, 70000, 120000, 200000, 500000, 800000, 1600000};

    public Container printShardAmount(int firstLevel, int secondLevel, String category) {
        int amount = 0;
        if (secondLevel > firstLevel) {
            for (int i = firstLevel; i <= secondLevel; i++) {
                amount += EXP_BY_LEVEL[i];
            }
        }
        switch (category) {
            case "Élite":
                amount *= 0.75; break;
            case "Ordinaire":
                amount *= 0.5;
        }

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.addComponent(new Label("Expérience à acquérir : " + NumberFormat.getIntegerInstance().format(amount)));
        c.addComponent(new Label("Fragments à payer : " + NumberFormat.getIntegerInstance().format(amount/20)));

        return c;
    }
}
