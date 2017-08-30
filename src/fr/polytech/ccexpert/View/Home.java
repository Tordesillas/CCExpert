package fr.polytech.ccexpert.View;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;

public class Home extends Form implements ActionListener {
    private CCExpert main;
    private Command quit;
    private Button b1;
    private Button b2;
    private Button b3;

    public Home(CCExpert main) {
        this.main = main;

        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
        setLayout(mainLayout);
        setTitle("CCExpert");

        Container actions = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        b1 = new Button("Héros", main.getTheme().getImage("unicorn.jpg"));
        b2 = new Button("GdG", main.getTheme().getImage("unicorn.jpg"));
        b3 = new Button("Donjons", main.getTheme().getImage("unicorn.jpg"));
        actions.addComponent(b1);
        actions.addComponent(b2);
        actions.addComponent(b3);
        addComponent(BorderLayout.CENTER, actions);

        quit = new Command("Quitter");
        addCommand(quit);
        setBackCommand(quit);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Command cmd = evt.getCommand();
        Object obj = evt.getSource();

        if (obj == b1) {
            main.loadHeroes();
        } else if (obj == b2) {
            main.loadGdG();
        } else if (obj == b3) {
            main.loadDonjons();
        }

        if (cmd == quit) {
            Display.getInstance().exitApplication();
        }
    }
}
