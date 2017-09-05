package fr.polytech.ccexpert.view;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Hero;

class HeroDisplay extends Form implements ActionListener {
    private CCExpert main;
    private Command back;

    HeroDisplay(CCExpert main, Hero hero) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle(hero.getName());

        Label nameLabel = new Label("Nom : " + hero.getName());
        Label data1 = new Label("Data");
        Label data2 = new Label("Data again");
        addComponent(nameLabel);
        addComponent(data1);
        addComponent(data2);

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);
        setToolbar(main.setToolbar(getToolbar()));
        addCommandListener(this);

        show();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getCommand() == back) {
            main.loadHeroes();
        }
    }
}
