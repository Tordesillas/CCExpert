package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.DodgeProcessor;

public class DodgeSimulator extends Simulator {
    private DodgeProcessor dp;
    private TextField basicDodge;
    private TextField extraDodge;
    private Container talentContainer;
    private Container artefactContainer;
    private Container res;

    public DodgeSimulator(CCExpert main) {
        super(main);
        setTitle("Esquive");
        dp = new DodgeProcessor();
        addCommandListener(this);

        addComponent(new SpanLabel("Renseignez l'esquive de base du héros :"));
        basicDodge = new TextField("", "Insérer l'esquive du héros", 6, TextArea.NUMERIC);
        addComponent(basicDodge);

        addComponent(new SpanLabel("Le héros possède-t'il Brûlure ?"));
        talentContainer = switchWithSpinner(1, 9, "Talent_Scorch.png");
        addComponent(talentContainer);

        addComponent(new SpanLabel("Le héros possède-t'il l'artefact Pierre de Foudre ?"));
        artefactContainer = switchWithPicture("pierre_de_foudre.png");
        addComponent(artefactContainer);

        addComponent(new SpanLabel("Le héros possède-t'il des équipements d'esquive ?"));
        extraDodge = new TextField("", "Insérer l'esquive ajoutée par les équipements", 6, TextArea.NUMERIC);
        addComponent(extraDodge);

        lookFor.addActionListener(this);
        addComponent(lookFor);

        res = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(res);
    }

    private void howManyDodge() {
        int dodge1 = parseInteger(basicDodge.getText().trim());
        int dodge2 = parseInteger(extraDodge.getText().trim());
        basicDodge.clear();
        extraDodge.clear();
        Container tmp = dp.printDodgeAmount(dodge1, getValueSwitchSpinner(talentContainer), getValueSwitch(artefactContainer), dodge2);
        replace(res, tmp, CommonTransitions.createFade(300));
        res = tmp;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object obj = evt.getSource();

        if (obj == lookFor) {
            howManyDodge();
            show();
        }
        if (evt.getCommand() == back) {
            main.loadSimulators();
        }
    }
}
