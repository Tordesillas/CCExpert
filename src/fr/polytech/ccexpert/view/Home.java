package fr.polytech.ccexpert.view;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.MathUtil;
import fr.polytech.ccexpert.CCExpert;

public class Home extends Form implements ActionListener {
    private CCExpert main;
    private Command quit;
    private Button buttonHeroes;
    private Button buttonSimulators;
    private Button buttonDungeons;
    private Button buttonFeatures;

    public Home(CCExpert main) {
        this.main = main;

        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
        setLayout(mainLayout);
        setTitle("CCExpert");

        Container actions = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        actions.setScrollableY(true);
        buttonHeroes = new Button("Héros", resizePicture(main.getTheme().getImage("heroes.png")));
        buttonSimulators = new Button("Simulateur", resizePicture(main.getTheme().getImage("boss.png")));
        buttonDungeons = new Button("Donjons", resizePicture(main.getTheme().getImage("donjons.png")));
        buttonFeatures = new Button("En savoir plus", resizePicture(main.getTheme().getImage("urodia.png")));
        actions.addComponent(buttonHeroes);
        actions.addComponent(buttonSimulators);
        actions.addComponent(buttonDungeons);
        actions.addComponent(buttonFeatures);
        addComponent(BorderLayout.CENTER, actions);

        quit = new Command("Quitter");
        addCommand(quit);
        setBackCommand(quit);
        addCommandListener(this);

        buttonHeroes.addActionListener(this);
        buttonSimulators.addActionListener(this);
        buttonDungeons.addActionListener(this);
        buttonFeatures.addActionListener(this);
    }

    private Image resizePicture(Image image) {
        return image.scaledHeight(MathUtil.round(Display.getInstance().getDisplayHeight() / (float)5.3));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Command cmd = evt.getCommand();
        Object obj = evt.getSource();

        if (obj == buttonHeroes) {
            main.loadHeroes();
        } else if (obj == buttonSimulators) {
            main.loadSimulators();
        } else if (obj == buttonDungeons) {
            main.loadDungeons();
        } else if (obj == buttonFeatures) {
            main.loadFeatToCome();
        }

        if (cmd == quit) {
            Display.getInstance().exitApplication();
        }
    }
}
