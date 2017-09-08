package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.OnOffSwitch;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.NumericSpinner;
import fr.polytech.ccexpert.CCExpert;

abstract class Simulator extends Form implements ActionListener {
    Button lookFor;
    protected CCExpert main;
    protected Command back;

    Simulator(CCExpert main) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle(" ");

        lookFor = new Button("Analyser");

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);
        setToolbar(main.setToolbar(getToolbar()));
    }

    Container switchWithSpinner(int min, int max, String picture) {
        Container container = new Container(new BoxLayout(BoxLayout.X_AXIS));
        OnOffSwitch aSwitch = new OnOffSwitch();
        aSwitch.setValue(false);
        container.addComponent(aSwitch);
        container.addComponent(new Label(main.getTheme().getImage(picture)));
        NumericSpinner spinner = new NumericSpinner();
        spinner.setMin(min);
        spinner.setMax(max);
        spinner.setStep(1);
        spinner.setVisible(false);
        container.addComponent(spinner);
        aSwitch.addActionListener(evt -> {
            spinner.setVisible(aSwitch.isValue());
            show();
        });
        return container;
    }

    Container switchWithPicture(String picture) {
        Container container = new Container(new BoxLayout(BoxLayout.X_AXIS));
        OnOffSwitch aSwitch = new OnOffSwitch();
        container.addComponent(aSwitch);
        container.addComponent(new Label(main.getTheme().getImage(picture)));
        return container;
    }

    int getValueSwitchSpinner(Container container) {
        boolean switchValue = false;
        Component component;
        int value = 0;
        for (int i = 0; i < container.getComponentCount(); i++) {
            component = container.getComponentAt(i);
            if (component instanceof OnOffSwitch) {
                switchValue = ((OnOffSwitch) component).isValue();
            }
            if (component instanceof NumericSpinner) {
                value = (int)((NumericSpinner) component).getValue();
            }
        }
        return switchValue ? value : 0;
    }

    boolean getValueSwitch(Container container) {
        boolean switchValue = false;
        Component component;
        for (int i = 0; i < container.getComponentCount(); i++) {
            component = container.getComponentAt(i);
            if (component instanceof OnOffSwitch) {
                switchValue = ((OnOffSwitch) component).isValue();
            }
        }
        return switchValue;
    }

    int parseInteger(String n) {
        try {
            return Integer.parseInt(n);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
