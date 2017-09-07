package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.MultiButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.NumericSpinner;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.AttackSpeedProcessor;

public class AttackSpeedSimulator extends Form implements ActionListener {
    private Button lookFor;
    private CCExpert main;
    private Command back;
    private AttackSpeedProcessor asp;
    private MultiButton buttonHero;
    private OnOffSwitch talentSwitch;
    private OnOffSwitch secondTalentSwitch;
    private OnOffSwitch dukeSwitch;
    private NumericSpinner lvlTalent;
    private NumericSpinner dukeLvlTalent;
    private NumericSpinner hitsDuke;
    private OnOffSwitch artefactSwitch;

    public AttackSpeedSimulator(CCExpert main) {
        this.main = main;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("Vitesse d'attaque");
        asp = new AttackSpeedProcessor();

        addComponent(new Label("Héros à analyser :"));
        buildSelector();
        addComponent(buttonHero);

        addComponent(new SpanLabel("Le héros possède-t'il Fou Furieux ?"));
        Container talentContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        talentSwitch = new OnOffSwitch();
        talentSwitch.setValue(false);
        talentContainer.addComponent(talentSwitch);
        talentContainer.addComponent(new Label(main.getTheme().getImage("unicorn.jpg")));
        lvlTalent = new NumericSpinner();
        lvlTalent.setMin(1);
        lvlTalent.setMax(9);
        lvlTalent.setStep(1);
        lvlTalent.setVisible(false);
        talentContainer.addComponent(lvlTalent);
        talentSwitch.addActionListener(evt -> {
            lvlTalent.setVisible(talentSwitch.isValue());
            show();
        });
        addComponent(talentContainer);

        addComponent(new SpanLabel("Le héros possède-t'il l'artefact Blitzkrieg ?"));
        Container artefactContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        artefactSwitch = new OnOffSwitch();
        artefactContainer.addComponent(artefactSwitch);
        artefactContainer.addComponent(new Label(main.getTheme().getImage("unicorn.jpg")));
        addComponent(artefactContainer);

        addComponent(new SpanLabel("Le héros possède-t'il en second talent Fureur ?"));
        Container secondTalentContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        secondTalentSwitch = new OnOffSwitch();
        secondTalentContainer.addComponent(secondTalentSwitch);
        secondTalentContainer.addComponent(new Label(main.getTheme().getImage("unicorn.jpg")));
        addComponent(secondTalentContainer);

        addComponent(new SpanLabel("Le héros a t'il à ses côtés un duc ?"));
        Container dukeContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        dukeSwitch = new OnOffSwitch();
        dukeSwitch.setValue(false);
        dukeContainer.addComponent(dukeSwitch);
        dukeContainer.addComponent(new Label(main.getTheme().getImage("unicorn.jpg")));
        dukeLvlTalent = new NumericSpinner();
        dukeLvlTalent.setMin(1);
        dukeLvlTalent.setMax(11);
        dukeLvlTalent.setStep(1);
        dukeLvlTalent.setVisible(false);
        talentContainer.addComponent(dukeLvlTalent);
        dukeSwitch.addActionListener(evt -> {
            dukeLvlTalent.setVisible(dukeSwitch.isValue());
            show();
        });
        addComponent(dukeContainer);

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);
        setToolbar(main.setToolbar(getToolbar()));
        addCommandListener(this);
    }

    private void buildSelector() {
        String[] heroes = { "Skeletica", "Duc", "Mickaël", "Ducilia"};

        buttonHero = new MultiButton("Choisis un héros");
        buttonHero.addActionListener(e -> {
            Dialog d = new Dialog();
            d.setLayout(BoxLayout.y());
            d.getContentPane().setScrollableY(true);
            for (String hero : heroes) {
                MultiButton mb = new MultiButton(hero);
                mb.setIcon(main.getTheme().getImage("unicorn.jpg"));
                d.add(mb);
                mb.addActionListener(evt -> {
                    buttonHero.setTextLine1(mb.getTextLine1());
                    buttonHero.setIcon(mb.getIcon());
                    d.dispose();
                    buttonHero.revalidate();
                });
            }
            d.showPopupDialog(buttonHero);
        });
    }

    private void howManyAttackSpeed() {

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object obj = evt.getSource();

        if (obj == lookFor) {
            howManyAttackSpeed();
            show();
        }
        if (evt.getCommand() == back) {
            main.loadSimulators();
        }
    }
}
