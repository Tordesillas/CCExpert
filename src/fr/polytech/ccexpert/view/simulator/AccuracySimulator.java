package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.AccuracyProcessor;

public class AccuracySimulator extends Simulator {
    private AccuracyProcessor ap;
    private TextField basicAccuracy;
    private Container artefactContainer;
    private TextField extraAccuracy;
    private Container res;

    public AccuracySimulator(CCExpert main) {
        super(main);
        setTitle("Précision");
        ap = new AccuracyProcessor();
        addCommandListener(this);

        addComponent(new SpanLabel("Renseignez la précision de base du héros :"));
        basicAccuracy = new TextField("", "Insérer la précision du héros", 6, TextArea.NUMERIC);
        addComponent(basicAccuracy);

        addComponent(new SpanLabel("Le héros possède-t'il l'artefact Garuda ?"));
        artefactContainer = switchWithPicture("unicorn.jpg");
        addComponent(artefactContainer);

        addComponent(new SpanLabel("Le héros possède-t'il des équipements de précision ?"));
        extraAccuracy = new TextField("", "Insérer la précision ajoutée par les équipements", 6, TextArea.NUMERIC);
        addComponent(extraAccuracy);

        lookFor.addActionListener(this);
        addComponent(lookFor);

        res = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(res);
    }

    private void howManyAccuracy() {
        int accu1 = parseInteger(basicAccuracy.getText().trim());
        int accu2 = parseInteger(extraAccuracy.getText().trim());
        basicAccuracy.clear();
        extraAccuracy.clear();
        Container tmp = ap.printAccuracyAmount(accu1, getValueSwitch(artefactContainer), accu2);
        replace(res, tmp, CommonTransitions.createFade(300));
        res = tmp;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == lookFor) {
            howManyAccuracy();
            show();
        }
        if (evt.getCommand() == back) {
            main.loadSimulators();
        }
    }
}
