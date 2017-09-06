package fr.polytech.ccexpert.view.dungeon;

import com.codename1.components.MultiButton;
import com.codename1.io.URL;
import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Crest;
import fr.polytech.ccexpert.model.Dungeon;
import fr.polytech.ccexpert.model.Hero;

import java.util.ArrayList;

public class Dungeons extends Form implements ActionListener {
    private CCExpert main;
    private Command back;

    public Dungeons(CCExpert main) {
        this.main = main;
        setLayout(new GridLayout(8, 3));

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);

        setTitle("Donjons");
        main.setToolbar(getToolbar());

        addCommandListener(this);

        try {
            ArrayList<Hero> heroes = new ArrayList<>();
            heroes.add(new Hero("Skeletica", main.getTheme().getImage("unicorn.jpg")));
            heroes.add(new Hero("Skeletica", main.getTheme().getImage("unicorn.jpg")));
            heroes.add(new Hero("Skeletica", main.getTheme().getImage("unicorn.jpg")));
            heroes.add(new Hero("Skeletica", main.getTheme().getImage("unicorn.jpg")));
            heroes.add(new Hero("Skeletica", main.getTheme().getImage("unicorn.jpg")));
            heroes.add(new Hero("Skeletica", main.getTheme().getImage("unicorn.jpg")));
            ArrayList<Crest> crests = new ArrayList<>();
            crests.add(new Crest("Berzerk", "Bah ça berserk", main.getTheme().getImage("unicorn.jpg")));
            crests.add(new Crest("Berzerk", "Bah ça berserk", main.getTheme().getImage("unicorn.jpg")));
            crests.add(new Crest("Berzerk", "Bah ça berserk", main.getTheme().getImage("unicorn.jpg")));
            crests.add(new Crest("Berzerk", "Bah ça berserk", main.getTheme().getImage("unicorn.jpg")));
            crests.add(new Crest("Berzerk", "Bah ça berserk", main.getTheme().getImage("unicorn.jpg")));
            crests.add(new Crest("Berzerk", "Bah ça berserk", main.getTheme().getImage("unicorn.jpg")));
            addComponent(createButtonDungeon(new Dungeon(new URL("https://www.youtube.com/watch?v=fdgi_GH6e_g"), 6, 1, heroes, crests)));
            addComponent(createButtonDungeon(new Dungeon(new URL("http://youtube.com"), 6, 2, heroes, crests)));
            addComponent(createButtonDungeon(new Dungeon(new URL("http://youtube.com"), 6, 3, heroes, crests)));
            addComponent(createButtonDungeon(new Dungeon(new URL("http://youtube.com"), 6, 4, heroes, crests)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MultiButton createButtonDungeon(Dungeon d) {
        MultiButton infoDungeon = new MultiButton();
        infoDungeon.setTextLine1(d.getDoor() + " - " + d.getBase());
        infoDungeon.setTextLine2("F2P / P2W");
        infoDungeon.addActionListener(evt -> new DungeonDisplay(main, d));

        return infoDungeon;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getCommand() == back) {
            main.loadHome();
        }
    }
}
