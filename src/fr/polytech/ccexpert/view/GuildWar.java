package fr.polytech.ccexpert.view;

import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.StatsGuildWar;

public class GuildWar extends Form implements ActionListener {
    private CCExpert main;
    private Button lookFor;
    private Command back;
    private Container stats;
    private StatsGuildWar sgw;
    private TextField userPower;
    private TextField userScore;

    public GuildWar(CCExpert main) {
        this.main = main;
        setTitle("Guerre de guilde");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        Label labelPower = new Label("Puissance :");
        userPower = new TextField("", "Insérer la puissance du joueur", 6, TextArea.NUMERIC);
        Label labelScore = new Label("Score :");
        userScore = new TextField("", "Insérer le score du joueur", 4, TextArea.NUMERIC);

        lookFor = new Button("Analyser");
        lookFor.addActionListener(this);

        addComponent(labelPower);
        addComponent(userPower);
        addComponent(labelScore);
        addComponent(userScore);
        addComponent(lookFor);

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);

        Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);
        toolbar.setBackCommand(back, Toolbar.BackCommandPolicy.ALWAYS);
        addCommandListener(this);

        stats = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(stats);

        sgw = new StatsGuildWar();
    }

    private void loadStatistics() {
        int power;
        int score;
        try {
            power = Integer.parseInt(userPower.getText().trim().replace(" ", ""));
            score = Integer.parseInt(userScore.getText().trim().replace(" ", ""));
        } catch (NumberFormatException e) {
            power = 0;
            score = 0;
        }
        Container tmp = sgw.printStats(power, score);
        replace(stats, tmp, CommonTransitions.createFade(500));
        stats = tmp;
    }


    @Override
    public void actionPerformed(ActionEvent evt) {
        Command cmd = evt.getCommand();
        Object obj = evt.getSource();

        if (obj == lookFor) {
            loadStatistics();
            show();
        }

        if (cmd == back) {
            main.loadHome();
        }
    }
}
