package fr.polytech.ccexpert.view;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Toolbar.BackCommandPolicy;
import fr.polytech.ccexpert.CCExpert;
import fr.polytech.ccexpert.model.Hero;

public class HeroesSearch extends Form implements ActionListener {
    private CCExpert main;
    private Button searchButton;
    private TextField searchField;
    private Command back;
    private Tabs tabs;
    private Container containerHeroes;
    private Container containerSearch;

    public HeroesSearch(CCExpert main) {
        this.main = main;
        setLayout(new BorderLayout());

        containerHeroes = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        containerHeroes.setScrollableY(true);
        containerSearch = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        containerSearch.setScrollableY(true);

        tabs = new Tabs();
        tabs.setSwipeActivated(true);
        tabs.addTab("Tous les héros", containerHeroes);
        tabs.addTab("Recherche", containerSearch);
        addComponent(BorderLayout.CENTER, tabs);

        Container toolbarContainer = new Container(new BorderLayout());
        searchField = new TextField();
        searchField.setHint("Héros");
        searchButton = new Button(main.getTheme().getImage("unicorn.jpg"));
        toolbarContainer.addComponent(BorderLayout.CENTER, searchField);
        toolbarContainer.addComponent(BorderLayout.EAST, searchButton);

        back = new Command("Retour", FontImage.MATERIAL_ARROW_BACK);
        setBackCommand(back);

        Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);
        toolbar.setBackCommand(back, BackCommandPolicy.ALWAYS);
        toolbar.setTitleComponent(toolbarContainer);

        searchButton.addActionListener(this);
        addCommandListener(this);
        printAllHeroes();
    }

    private void rechercheHeros() {
        tabs.setSelectedIndex(1);
        String heroSearch = searchField.getText().trim();

        if (heroSearch.length() == 0) {
            Dialog.show("", "Champ vide", "Ok", null);
            return;
        }
        if (containerSearch.getComponentCount() > 0) {
            containerSearch.removeAll();
        }

        Dialog.show("Request", heroSearch, "Ok", null);
    }

    private void printAllHeroes() {
        if (containerHeroes.getComponentCount() > 0) {
            containerHeroes.removeAll();
        }
        // BdD
        Hero hero1 = new Hero("Miaou", main.getTheme().getImage("unicorn.jpg"));
        Hero hero2 = new Hero("Nyu", main.getTheme().getImage("unicorn.jpg"));

        createButtonHero(hero1, "Pdp / rempart / revita");
        createButtonHero(hero2, "Ressu / revita");
    }

    private void createButtonHero(Hero hero, String subtitle) {
        MultiButton infoHero = new MultiButton();
        infoHero.setEmblem(hero.getPicture());
        infoHero.setTextLine1(hero.getName());
        infoHero.setTextLine2(subtitle);
        infoHero.addActionListener(evt -> new HeroDisplay(main, hero));
        containerHeroes.addComponent(infoHero);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Command cmd = evt.getCommand();
        Object obj = evt.getSource();

        if (obj == searchButton) {
            rechercheHeros();
        }

        if (cmd == back) {
            main.loadHome();
        }
    }
}
