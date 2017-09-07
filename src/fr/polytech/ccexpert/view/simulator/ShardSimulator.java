package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.ShardProcessor;

public class ShardSimulator extends Form implements ActionListener {
    private Command back;
    private Button lookFor;
    private Container res;
    private NumericSpinner lvlStart;
    private NumericSpinner lvlEnd;
    private ShardProcessor sp;
    private Picker category;
    private CCExpert main;

    public ShardSimulator(CCExpert main) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("Fragments");
        sp = new ShardProcessor();

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

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);
        setToolbar(main.setToolbar(getToolbar()));
        addCommandListener(this);
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
        Object obj = evt.getSource();

        if (obj == lookFor) {
            howManyShards();
            show();
        }
        if (evt.getCommand() == back) {
            main.loadSimulators();
        }
    }
}
