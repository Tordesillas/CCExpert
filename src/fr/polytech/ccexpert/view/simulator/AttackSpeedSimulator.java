package fr.polytech.ccexpert.view.simulator;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.controller.AttackSpeedProcessor;

public class AttackSpeedSimulator extends Simulator {
    private AttackSpeedProcessor asp;
    private MultiButton buttonHero;
    private Container ffContainer;
    private Container artefactContainer;
    private Container secondTalentContainer;
    private Container dukeContainer;
    private Container hitsContainer;
    private Container michaelContainer;
    private Container destroyerContainer;
    private Container res;

    public AttackSpeedSimulator(CCExpert main) {
        super(main);
        setTitle("Vitesse d'attaque");
        asp = new AttackSpeedProcessor();
        addCommandListener(this);

        addComponent(new Label("Héros à analyser :"));
        buildSelector();
        addComponent(buttonHero);

        addComponent(new SpanLabel("Le héros possède-t'il Fou Furieux ?"));
        ffContainer = switchWithSpinner(1, 9, "unicorn.jpg");
        addComponent(ffContainer);

        addComponent(new SpanLabel("Le héros possède-t'il l'artefact Blitzkrieg ?"));
        artefactContainer = switchWithPicture("unicorn.jpg");
        addComponent(artefactContainer);

        addComponent(new SpanLabel("Le héros possède-t'il en second talent Fureur ?"));
        secondTalentContainer = switchWithPicture("unicorn.jpg");
        addComponent(secondTalentContainer);

        addComponent(new SpanLabel("Le héros a t'il à ses côtés un Duc ?"));
        dukeContainer = switchWithSpinner(1, 11, "unicorn.jpg");
        addComponent(dukeContainer);

        addComponent(new SpanLabel("Combien de coups a effectué le Duc allié ?"));
        hitsContainer = switchWithSpinner(0, 5, "unicorn.jpg");
        addComponent(hitsContainer);

        addComponent(new SpanLabel("Le héros a t'il à ses côtés un Michaël ?"));
        michaelContainer = switchWithSpinner(1, 11, "unicorn.jpg");
        addComponent(michaelContainer);

        addComponent(new SpanLabel("Le héros a t'il à ses côtés un Destructeur ?"));
        destroyerContainer = switchWithSpinner(1, 11, "unicorn.jpg");
        addComponent(destroyerContainer);

        res = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(res);

        lookFor.addActionListener(this);
        addComponent(lookFor);
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
        String hero = buttonHero.getNameLine1();
        int ff = getValueSwitchSpinner(ffContainer);
        boolean blitz = getValueSwitch(artefactContainer);
        boolean fureur = getValueSwitch(secondTalentContainer);
        int duke = getValueSwitchSpinner(dukeContainer);
        int hits = getValueSwitchSpinner(hitsContainer);
        int mika = getValueSwitchSpinner(michaelContainer);
        int destr = getValueSwitchSpinner(destroyerContainer);
        Container tmp = asp.printAttackSpeedAmount(hero, ff, blitz, fureur, duke, hits, mika, destr);
        replace(res, tmp, CommonTransitions.createFade(300));
        res = tmp;
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
