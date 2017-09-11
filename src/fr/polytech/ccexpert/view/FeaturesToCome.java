package fr.polytech.ccexpert.view;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;

public class FeaturesToCome extends Form implements ActionListener {
    private Button mail;
    private Command back;
    private CCExpert main;

    public FeaturesToCome(CCExpert main) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("En savoir plus");

        SpanLabel intro = new SpanLabel("La réalisation d'une application est un processus qui prend du temps.\n" +
                "Les nouvelles fonctionnalités prendront un moment à arriver alors soyez patients.\n" +
                "En attendant la suite, vous pouvez toujours me soutenir sur les réseaux sociaux " +
                "ou en m'envoyant un mail ici :");
        mail = new Button(main.getTheme().getImage("mail.svg").scaledWidth(100));
        SpanLabel content = new SpanLabel("\nVoici la liste des nouvelles fonctionnalités en cours :\n\n" +
                "- Simulateur pour héros (talents, attributs, équipements...)\n" +
                "- Traduction en anglais\n" +
                "- Page de conseils\n\n" +
                "N'hésitez pas à me proposer ce que voudriez voir ici.\n" +
                "Merci pour votre soutien.\n");
        Label picture = new Label(main.getTheme().getImage("exorcist.png"));

        mail.addActionListener(this);

        addComponent(intro);
        addComponent(mail);
        addComponent(content);
        addComponent(picture);

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

        if (obj == mail) {
            Display.getInstance().execute("mailto:gcasagrande2812@gmail.com");
        }
    }
}
