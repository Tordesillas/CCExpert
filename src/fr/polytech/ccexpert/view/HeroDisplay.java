package fr.polytech.ccexpert.view;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Hero;

class HeroDisplay extends Form {
    HeroDisplay(CCExpert main, Hero hero) {
        Form heroDisplay = new Form(hero.getName());
        heroDisplay.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        Label nameLabel = new Label("Nom : " + hero.getName());
        Label data1 = new Label("Data");
        Label data2 = new Label("Data again");
        heroDisplay.addComponent(nameLabel);
        heroDisplay.addComponent(data1);
        heroDisplay.addComponent(data2);

        Button back = new Button("Retour");
        back.addActionListener(evt -> main.loadHeroes());
        heroDisplay.addComponent(back);

        heroDisplay.show();
    }
}
