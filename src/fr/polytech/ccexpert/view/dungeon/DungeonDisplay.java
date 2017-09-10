package fr.polytech.ccexpert.view.dungeon;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Crest;
import fr.polytech.ccexpert.model.Dungeon;
import fr.polytech.ccexpert.model.Hero;

import java.util.ArrayList;

class DungeonDisplay extends Form implements ActionListener {
    private CCExpert main;
    private Command back;

    DungeonDisplay(CCExpert main, Dungeon dungeon) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("Donjon " + dungeon.getDoor() + "-" + dungeon.getBase());

        MultiButton youtubeLink = new MultiButton("Lien YouTube");
        youtubeLink.setIcon(main.getTheme().getImage("youtube_black.png"));
        youtubeLink.addActionListener(evt -> Display.getInstance().execute(dungeon.getUrlYoutube().toString()));
        addComponent(youtubeLink);

        Label title = new Label("Donjon " + dungeon.getDoor() + " - " + dungeon.getBase());

        addComponent(title);
        addComponent(new Label("Héros utilisés dans la vidéo :"));
        addComponent(listHeroes(dungeon.getHeroes()));
        addComponent(new Label("Écussons utilisés dans la vidéo :"));
        addComponent(listCrests(dungeon.getCrests()));

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);
        setToolbar(main.setToolbar(getToolbar()));
        addCommandListener(this);

        show();
    }

    private Container listHeroes(ArrayList<Hero> heroes) {
        Container cHeroes = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cHeroes.setScrollableX(true);
        Label l;
        for (Hero hero : heroes) {
            l = new Label(hero.getName(), hero.getPicture());
            l.setTextPosition(Label.BOTTOM);
            cHeroes.addComponent(l);
        }
        return cHeroes;
    }

    private Container listCrests(ArrayList<Crest> crests) {
        Container cCrests = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cCrests.setScrollableX(true);
        Label l;
        for (Crest crest : crests) {
            l = new Label(crest.getName(), crest.getPicture());
            l.setTextPosition(Label.BOTTOM);
            cCrests.addComponent(l);
        }
        return cCrests;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getCommand() == back) {
            main.loadDungeons();
        }
    }
}
