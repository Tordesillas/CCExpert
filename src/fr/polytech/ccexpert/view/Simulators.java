package fr.polytech.ccexpert.view;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;

public class Simulators extends Form implements ActionListener {
    private Command back;
    private CCExpert main;

    public Simulators(CCExpert main) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        


        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);

        setToolbar(main.setToolbar(getToolbar()));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getCommand() == back) {
            main.loadHome();
        }
    }
}
