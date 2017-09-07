package fr.polytech.ccexpert.controller;

import com.codename1.l10n.L10NManager;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class DodgeProcessor {
    private L10NManager l10n;

    public DodgeProcessor() {
        l10n = L10NManager.getInstance();
    }

    public Container printDodgeAmount(int basic, boolean talentBool, int talentLvl, boolean artefactBool, int extra) {
        Container c = new Container();
        double amountsTalent[] = {0.05, 0.06, 0.07, 0.08, 0.09, 0.15, 0.2, 0.3};
        double amountTalent = (talentBool) ? amountsTalent[talentLvl-1] : 0;
        double amountArtefact = (artefactBool) ? 1 : 0;
        double amount = amountTalent + (1-amountTalent)*amountArtefact + (1-amountTalent)*(1-amountArtefact)*(basic+extra)/10000;

        c.addComponent(new Label("Esquive totale : " + l10n.format(amount)));

        return c;
    }
}
