package fr.polytech.ccexpert.view.dungeon;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.MathUtil;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.*;

import java.util.ArrayList;

class DungeonDisplay extends Form implements ActionListener {
    private CCExpert main;
    private Sets sets;
    private Command back;

    DungeonDisplay(CCExpert main, Dungeon dungeon) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("Donjon " + dungeon.getDoor() + "-" + dungeon.getBase());
        sets = main.getSets();

        MultiButton youtubeLink = new MultiButton("Lien YouTube");
        youtubeLink.setIcon(main.getTheme().getImage("youtube.svg").scaledWidth(MathUtil.round(Display.getInstance().getDisplayWidth() / 3)));
        youtubeLink.addActionListener(evt -> Display.getInstance().execute(dungeon.getUrlYoutube().toString()));
        addComponent(youtubeLink);

        Label title = new Label("Donjon " + dungeon.getDoor() + " - " + dungeon.getBase());

        addComponent(title);
        addComponent(new Label("Détails des héros utilisés dans la vidéo :"));
        addComponent(listHeroes(dungeon.getHeroesIds()));

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);
        setToolbar(main.setToolbar(getToolbar()));
        addCommandListener(this);

        show();
    }

    private Container listHeroes(int[] ids) {
        Container cHeroes = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cHeroes.setScrollableX(true);

        Container cHero;
        HeroFaculties heroFaculties;
        Hero hero;
        Label pic;
        SpanLabel text;

        for (int id : ids) {
            cHero = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cHero.getStyle().setAlignment(CENTER);
            heroFaculties = sets.getHeroFaculties(id);
            hero = sets.getHero(heroFaculties.getHeroId());
            pic = new Label(hero.getPicture().scaledWidth(MathUtil.round(Display.getInstance().getDisplayWidth() / 4)));
            text = new SpanLabel(hero.getFrenchName());

            cHero.addComponent(pic);
            cHero.addComponent(text);

            if (heroFaculties.getLevel() != 0) {
                cHero.addComponent(new Label("Niveau " + heroFaculties.getLevel()));
            }

            if (heroFaculties.getInscription() != 0) {
                cHero.addComponent(new Label("Gravé " + heroFaculties.getLevel()));
            }

            cHeroes.addComponent(cHero);
        }
        return cHeroes;
    }

    private Container listCrests(ArrayList<Crest> crests) {
        Container cCrests = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cCrests.setScrollableX(true);
        Label l;
        for (Crest crest : crests) {
            l = new Label(crest.getName(), crest.getPicture().scaledWidth(MathUtil.round(Display.getInstance().getDisplayWidth() / 4)));
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
