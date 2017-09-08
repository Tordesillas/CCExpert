package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.spinner.NumericSpinner;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.CrystalProcessor;

public class CrystalSimulator extends Simulator {
    private Container res;
    private NumericSpinner lvlStart;
    private NumericSpinner lvlEnd;
    private CrystalProcessor cp;

    public CrystalSimulator(CCExpert main) {
        super(main);
        setTitle("Cristaux bleus");
        cp = new CrystalProcessor();
        addCommandListener(this);

        SpanLabel intro = new SpanLabel("Niveau actuel du héros :");
        lvlStart = new NumericSpinner();
        lvlStart.setMin(0);
        lvlStart.setMax(100);
        lvlStart.setStep(1);
        Label text = new Label("Niveau à atteindre :");
        lvlEnd = new NumericSpinner();
        lvlEnd.setMin(1);
        lvlEnd.setMax(101);
        lvlEnd.setStep(1);

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

    private void howManyCrystals() {
        int firstLevel = (int)lvlStart.getValue();
        int secondLevel = (int)lvlEnd.getValue();
        Container tmp = cp.printCrystalAmount(firstLevel, secondLevel);
        replace(res, tmp, CommonTransitions.createFade(300));
        res = tmp;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == lookFor) {
            howManyCrystals();
            show();
        }
        if (evt.getCommand() == back) {
            main.loadSimulators();
        }
    }
}
