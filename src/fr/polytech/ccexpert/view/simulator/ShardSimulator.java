package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.ShardProcessor;

public class ShardSimulator extends Simulator {
    private NumericSpinner lvlStart;
    private NumericSpinner lvlEnd;
    private ShardProcessor sp;
    private Picker category;
    private Container res;

    public ShardSimulator(CCExpert main) {
        super(main);
        setTitle("Fragments");
        sp = new ShardProcessor();
        addCommandListener(this);

        SpanLabel intro = new SpanLabel("Niveau actuel du héros :");
        lvlStart = new NumericSpinner();
        lvlStart.setMin(1);
        lvlStart.setMax(10);
        lvlStart.setStep(1);
        Label text = new Label("Niveau à atteindre :");
        lvlEnd = new NumericSpinner();
        lvlEnd.setMin(2);
        lvlEnd.setMax(11);
        lvlEnd.setStep(1);
        category = new Picker();
        category.setType(Display.PICKER_TYPE_STRINGS);
        category.setStrings("Légendaire", "Élite", "Ordinaire");
        category.setSelectedString("Légendaire");
        lookFor = new Button("Analyser");
        lookFor.addActionListener(this);

        res = new Container();

        addComponent(intro);
        addComponent(lvlStart);
        addComponent(text);
        addComponent(lvlEnd);
        addComponent(category);
        addComponent(lookFor);
        addComponent(res);
    }

    private void howManyShards() {
        int firstLevel = (int)lvlStart.getValue();
        int secondLevel = (int)lvlEnd.getValue();
        Container tmp = sp.printShardAmount(firstLevel, secondLevel, category.getSelectedString());
        replace(res, tmp, CommonTransitions.createFade(300));
        res = tmp;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == lookFor) {
            howManyShards();
            show();
        }
        if (evt.getCommand() == back) {
            main.loadSimulators();
        }
    }
}
