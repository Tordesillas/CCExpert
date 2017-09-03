package fr.polytech.ccexpert.view;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;

import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Dungeon;
import fr.polytech.ccexpert.model.Hero;

import java.util.List;

class DungeonDisplay extends Form {

    DungeonDisplay(CCExpert main, Dungeon dungeon) {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        MultiButton youtubeLink = new MultiButton("Voir la vidéo sur YouTube");
        youtubeLink.setIcon(main.getTheme().getImage("unicorn.jpg"));
        youtubeLink.addActionListener(evt -> Display.getInstance().execute(dungeon.getUrlYoutube().toString()));
        addComponent(youtubeLink);

        Label title = new Label("Donjon " + dungeon.getDoor() + " - " + dungeon.getBase());
        addComponent(title);

        Command back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);

        Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);
        toolbar.setBackCommand(back, Toolbar.BackCommandPolicy.ALWAYS);
        toolbar.setTitle("Donjon " + dungeon.getDoor() + "-" + dungeon.getBase());
        addCommandListener(evt -> main.loadDungeons());

        addComponent(listHeroes(dungeon.getHeroes()));
        addComponent(listCrests(dungeon.getHeroes()));

        show();
    }

    private Container listHeroes(List<Hero> heroes) {
        return new Container(new BoxLayout(BoxLayout.X_AXIS));
    }

    private Container listCrests(List<Hero> heroes) {
        return new Container(new BoxLayout(BoxLayout.X_AXIS));
    }
}
