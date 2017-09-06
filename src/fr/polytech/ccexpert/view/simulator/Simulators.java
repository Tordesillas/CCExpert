package fr.polytech.ccexpert.view.simulator;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;

public class Simulators extends Form implements ActionListener {
    private Button buttonGuildWar;
    private Button buttonShards;
    private Button buttonCrystals;
    private Command back;
    private CCExpert main;

    public Simulators(CCExpert main) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
        setLayout(mainLayout);
        setTitle("Simulateurs");

        Container actions = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonGuildWar = new Button("Guerre de Guilde", main.getTheme().getImage("unicorn.jpg"));
        buttonShards = new Button("Fragments", main.getTheme().getImage("unicorn.jpg"));
        buttonCrystals = new Button("Cristaux bleus", main.getTheme().getImage("unicorn.jpg"));
        actions.addComponent(buttonGuildWar);
        actions.addComponent(buttonShards);
        actions.addComponent(buttonCrystals);
        buttonGuildWar.addActionListener(this);
        buttonShards.addActionListener(this);
        buttonCrystals.addActionListener(this);
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
        }
    }
}
