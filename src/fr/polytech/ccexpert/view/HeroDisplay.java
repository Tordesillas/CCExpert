package fr.polytech.ccexpert.view;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Hero;

class HeroDisplay extends Form {
    HeroDisplay(CCExpert main, Hero hero) {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        Label nameLabel = new Label("Nom : " + hero.getName());
        Label data1 = new Label("Data");
        Label data2 = new Label("Data again");
        addComponent(nameLabel);
        addComponent(data1);
        addComponent(data2);

        Command back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);

        Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);
        toolbar.setBackCommand(back, Toolbar.BackCommandPolicy.ALWAYS);
        toolbar.setTitle(hero.getName());
        addCommandListener(evt -> main.loadHeroes());

        show();
    }
}
