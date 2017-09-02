package fr.polytech.ccexpert.view;

import com.codename1.components.MultiButton;
import com.codename1.io.URL;
import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Dungeon;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class Dungeons extends Form implements ActionListener {
    private CCExpert main;
    private Command back;

    public Dungeons(CCExpert main) {
        this.main = main;
        setLayout(new GridLayout(8, 3));

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);

        Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);
        toolbar.setBackCommand(back, Toolbar.BackCommandPolicy.ALWAYS);
        toolbar.setTitle("Donjons");
        addCommandListener(this);

        try {
            addComponent(createButtonDungeon(new Dungeon(new URL("https://www.youtube.com/watch?v=fdgi_GH6e_g"), 6, 1, new ArrayList<>())));
            addComponent(createButtonDungeon(new Dungeon(new URL("http://youtube.com"), 6, 2, new ArrayList<>())));
            addComponent(createButtonDungeon(new Dungeon(new URL("http://youtube.com"), 6, 3, new ArrayList<>())));
            addComponent(createButtonDungeon(new Dungeon(new URL("http://youtube.com"), 6, 4, new ArrayList<>())));
        } catch (URISyntaxException e) {
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
        Command cmd = evt.getCommand();
        Object obj = evt.getSource();

        if (cmd == back) {
            main.loadHome();
        }
    }
}
