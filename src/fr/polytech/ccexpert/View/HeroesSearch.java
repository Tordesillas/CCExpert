package fr.polytech.ccexpert.View;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Toolbar.BackCommandPolicy;
import fr.polytech.ccexpert.CCExpert;

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
        createButtonHero("Nom du héros", "Pdp / rempart / revita", main.getTheme().getImage("unicorn.jpg"));
        createButtonHero("Nyu", "Ressu / revita", main.getTheme().getImage("unicorn.jpg"));
    }

    private void createButtonHero(String name, String subtitle, Image picture) {
        MultiButton infoHero = new MultiButton();
        infoHero.setEmblem(picture);
        infoHero.setTextLine1(name);
        infoHero.setTextLine2(subtitle);
        infoHero.addActionListener(evt -> printHero(name));
        containerHeroes.addComponent(infoHero);
    }

    private void printHero(String name) {
        Form hero = new Form(name);
        hero.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        SpanLabel nameLabel = new SpanLabel("Nom : " + name);
        Label data1 = new Label("Data");
        Label data2 = new Label("Data again");
        hero.addComponent(nameLabel);
        hero.addComponent(data1);
        hero.addComponent(data2);

        Button back = new Button("Retour");
        back.addActionListener(evt -> main.loadHeroes());
        hero.addComponent(back);

        hero.show();
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
