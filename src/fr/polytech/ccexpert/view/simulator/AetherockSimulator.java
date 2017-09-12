package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.spinner.NumericSpinner;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.AetherockProcessor;

public class AetherockSimulator extends Simulator {
    private AetherockProcessor ap;
    private NumericSpinner lvlStart;
    private NumericSpinner lvlEnd;
    private Container res;

    public AetherockSimulator(CCExpert main) {
        super(main);
        setTitle("Aura-guerrières");
        ap = new AetherockProcessor();
        addCommandListener(this);

        SpanLabel intro = new SpanLabel("Niveau actuel du héros :");
        lvlStart = createSpinner(1, 20);
        Label text = new Label("Niveau à atteindre :");
        lvlEnd = createSpinner(2, 21);

        lookFor = new Button("Analyser");
        lookFor.addActionListener(this);

        res = new Container();

        addComponent(intro);
        addComponent(lvlStart);
        addComponent(text);
        addComponent(lvlEnd);
        addComponent(lookFor);
        addComponent(res);
    }

    private void howManyAetherocks() {
        Container tmp = ap.printAetherockAmount((int)lvlStart.getValue(), (int)lvlEnd.getValue());
        replace(res, tmp, CommonTransitions.createFade(300));
        res = tmp;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == lookFor) {
            howManyAetherocks();
            show();
        }
        if (evt.getCommand() == back) {
            main.loadSimulators();
        }
    }
}
