package fr.polytech.ccexpert.view.simulator;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;

public class Simulators extends Form implements ActionListener {
    private CCExpert main;
    private Command back;

    private Button buttonGuildWar;
    private Button buttonShards;
    private Button buttonCrystals;
    private Button buttonDodge;
    private Button buttonAccuracy;
    private Button buttonAttackSpeed;

    public Simulators(CCExpert main) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
        setLayout(mainLayout);
        setTitle("Simulateurs");

        Container actions = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        actions.setScrollableY(true);
        buttonGuildWar = new Button("Guerre de guilde", main.getTheme().getImage("guild_wars.png"));
        buttonShards = new Button("Fragments", main.getTheme().getImage("shards.png"));
        buttonCrystals = new Button("Cristaux bleus", main.getTheme().getImage("crystal.png"));
        buttonDodge = new Button("Esquive", main.getTheme().getImage("pierre_de_foudre.png"));
        buttonAccuracy = new Button("Précision", main.getTheme().getImage("garuda.png"));
        buttonAttackSpeed = new Button("Vitesse d'attaque", main.getTheme().getImage("blitz.png"));
        actions.addComponent(buttonGuildWar);
        actions.addComponent(buttonShards);
        actions.addComponent(buttonCrystals);
        actions.addComponent(buttonDodge);
        actions.addComponent(buttonAccuracy);
        actions.addComponent(buttonAttackSpeed);
        buttonGuildWar.addActionListener(this);
        buttonShards.addActionListener(this);
        buttonCrystals.addActionListener(this);
        buttonDodge.addActionListener(this);
        buttonAccuracy.addActionListener(this);
        buttonAttackSpeed.addActionListener(this);
        addComponent(BorderLayout.CENTER, actions);

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);
        setToolbar(main.setToolbar(getToolbar()));
        addCommandListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object obj = evt.getSource();

        if (evt.getCommand() == back) {
            main.loadHome();
        }

        if (obj == buttonGuildWar) {
            main.loadGuildWar();
        } else if (obj == buttonShards) {
            main.loadShardSimulator();
        } else if (obj == buttonCrystals) {
            main.loadCrystalSimulator();
        } else if (obj == buttonDodge) {
            main.loadDodgeSimulator();
        } else if (obj == buttonAccuracy) {
            main.loadAccuracySimulator();
        } else if (obj == buttonAttackSpeed) {
            main.loadAttackSpeedSimulator();
        }
    }
}
