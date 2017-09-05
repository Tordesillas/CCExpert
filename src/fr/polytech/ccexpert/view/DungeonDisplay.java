package fr.polytech.ccexpert.view;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Dungeon;
import fr.polytech.ccexpert.model.Hero;

import java.util.List;

class DungeonDisplay extends Form implements ActionListener {
    private CCExpert main;
    private Command back;

    DungeonDisplay(CCExpert main, Dungeon dungeon) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("Donjon " + dungeon.getDoor() + "-" + dungeon.getBase());

        MultiButton youtubeLink = new MultiButton("Voir la vidéo sur YouTube");
        youtubeLink.setIcon(main.getTheme().getImage("unicorn.jpg"));
        youtubeLink.addActionListener(evt -> Display.getInstance().execute(dungeon.getUrlYoutube().toString()));
        addComponent(youtubeLink);

        Label title = new Label("Donjon " + dungeon.getDoor() + " - " + dungeon.getBase());

        addComponent(title);
        addComponent(listHeroes(dungeon.getHeroes()));
        addComponent(listCrests(dungeon.getHeroes()));

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);
        setToolbar(main.setToolbar(getToolbar()));
        addCommandListener(this);

        show();
    }

    private Container listHeroes(List<Hero> heroes) {
        return new Container(new BoxLayout(BoxLayout.X_AXIS));
    }

    private Container listCrests(List<Hero> heroes) {
        return new Container(new BoxLayout(BoxLayout.X_AXIS));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getCommand() == back) {
            main.loadDungeons();
        }
    }
}
