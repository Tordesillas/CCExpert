package fr.polytech.ccexpert.view.hero;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
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
        setTitle("Héros");

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

        main.setToolbar(getToolbar());
        getToolbar().setTitleComponent(toolbarContainer);

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

        Hero hero1 = new Hero("Hello", main.getTheme().getImage("unicorn.jpg"));
        Hero hero2 = new Hero("World", main.getTheme().getImage("unicorn.jpg"));

        containerSearch.addComponent(createButtonHero(hero1, "Pdp / rempart / revita"));
        containerSearch.addComponent(createButtonHero(hero2, "Ressu / revita"));
    }

    private void printAllHeroes() {
        if (containerHeroes.getComponentCount() > 0) {
            containerHeroes.removeAll();
        }
        // BdD
        Hero hero1 = new Hero("Miaou", main.getTheme().getImage("unicorn.jpg"));
        Hero hero2 = new Hero("Nyu", main.getTheme().getImage("unicorn.jpg"));

        containerHeroes.addComponent(createButtonHero(hero1, "Pdp / rempart / revita"));
        containerHeroes.addComponent(createButtonHero(hero2, "Ressu / revita"));
    }

    private MultiButton createButtonHero(Hero hero, String subtitle) {
        MultiButton infoHero = new MultiButton();
        infoHero.setEmblem(hero.getPicture());
        infoHero.setEmblem(main.getTheme().getImage("unicorn.jpg"));
        infoHero.setTextLine1(hero.getName());
        infoHero.setTextLine2(subtitle);
        infoHero.addActionListener(evt -> new HeroDisplay(main, hero));

        return infoHero;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Command cmd = evt.getCommand();
        Object obj = evt.getSource();

        if (obj == searchButton) {
            rechercheHeros();
            show();
        }

        if (cmd == back) {
            main.loadHome();
        }
    }
}
