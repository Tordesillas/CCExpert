package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.NumericSpinner;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.DodgeProcessor;

public class DodgeSimulator extends Form implements ActionListener {
    private Command back;
    private Button lookFor;
    private DodgeProcessor dp;
    private CCExpert main;
    private TextField basicDodge;
    private TextField extraDodge;
    private NumericSpinner lvlTalent;
    private OnOffSwitch artefactSwitch;
    private OnOffSwitch talentSwitch;
    private Container res;

    public DodgeSimulator(CCExpert main) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("Fragments");
        dp = new DodgeProcessor();

        addComponent(new SpanLabel("Renseignez l'esquive de base du héros :"));
        basicDodge = new TextField("", "Insérer l'esquive du héros", 6, TextArea.NUMERIC);
        addComponent(basicDodge);

        addComponent(new SpanLabel("Le héros possède-t'il roue à aubes ?"));
        Container talentContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        talentSwitch = new OnOffSwitch();
        talentSwitch.setValue(false);
        talentContainer.addComponent(talentSwitch);
        talentContainer.addComponent(new Label(main.getTheme().getImage("unicorn.jpg")));
        lvlTalent = new NumericSpinner();
        lvlTalent.setMin(1);
        lvlTalent.setMax(9);
        lvlTalent.setStep(1);
        lvlTalent.setVisible(false);
        talentContainer.addComponent(lvlTalent);
        talentSwitch.addActionListener(evt -> {
            lvlTalent.setVisible(talentSwitch.isValue());
            show();
        });
        addComponent(talentContainer);

        addComponent(new SpanLabel("Le héros possède-t'il l'artefact pierre de foudre ?"));
        Container artefactContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        artefactSwitch = new OnOffSwitch();
        artefactContainer.addComponent(artefactSwitch);
        artefactContainer.addComponent(new Label(main.getTheme().getImage("unicorn.jpg")));
        addComponent(artefactContainer);

        addComponent(new SpanLabel("Le héros possède-t'il des équipements de précision ?"));
        extraDodge = new TextField("", "Insérer l'esquive ajoutée par les équipements", 6, TextArea.NUMERIC);
        addComponent(extraDodge);

        lookFor = new Button("Analyser");
        lookFor.addActionListener(this);
        addComponent(lookFor);

        res = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(res);

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);
        setToolbar(main.setToolbar(getToolbar()));
        addCommandListener(this);
    }

    private void howManyDodge() {
        int dodge1 = parseInteger(basicDodge.getText().trim());
        int dodge2 = parseInteger(extraDodge.getText().trim());
        basicDodge.clear();
        extraDodge.clear();
        Container tmp = dp.printDodgeAmount(dodge1, talentSwitch.isValue(), (int)lvlTalent.getValue(), artefactSwitch.isValue(), dodge2);
        replace(res, tmp, CommonTransitions.createFade(300));
        res = tmp;
    }

    private int parseInteger(String n) {
        try {
            return Integer.parseInt(n);
        } catch (NumberFormatException e) {
            return 0;
        }
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
