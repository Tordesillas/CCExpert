package fr.polytech.ccexpert.view.dungeon;

import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Dungeon;

import java.util.List;

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

        List<Dungeon> dungeons = main.getSets().getDungeonSet();
        for (Dungeon d : dungeons) {
            addComponent(createButtonDungeon(d));
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
