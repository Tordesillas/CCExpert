package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.AccuracyProcessor;

public class AccuracySimulator extends Form implements ActionListener {
    private Command back;
    private Button lookFor;
    private CCExpert main;
    private AccuracyProcessor ap;
    private TextField basicAccuracy;
    private OnOffSwitch artefactSwitch;
    private TextField extraAccuracy;
    private Container res;

    public AccuracySimulator(CCExpert main) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("Précision");
        ap = new AccuracyProcessor();

        addComponent(new SpanLabel("Renseignez la précsion de base du héros :"));
        basicAccuracy = new TextField("", "Insérer la précision du héros", 6, TextArea.NUMERIC);
        addComponent(basicAccuracy);

        addComponent(new SpanLabel("Le héros possède-t'il l'artefact Garuda ?"));
        Container artefactContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        artefactSwitch = new OnOffSwitch();
        artefactContainer.addComponent(artefactSwitch);
        artefactContainer.addComponent(new Label(main.getTheme().getImage("unicorn.jpg")));
        addComponent(artefactContainer);

        addComponent(new SpanLabel("Le héros possède-t'il des équipements de précision ?"));
        extraAccuracy = new TextField("", "Insérer la précision ajoutée par les équipements", 6, TextArea.NUMERIC);
        addComponent(extraAccuracy);

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

    private void howManyAccuracy() {
        int accu1 = parseInteger(basicAccuracy.getText().trim());
        int accu2 = parseInteger(extraAccuracy.getText().trim());
        basicAccuracy.clear();
        extraAccuracy.clear();
        Container tmp = ap.printAccuracyAmount(accu1, artefactSwitch.isValue(), accu2);
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
            howManyAccuracy();
            show();
        }
        if (evt.getCommand() == back) {
            main.loadSimulators();
        }
    }
}
