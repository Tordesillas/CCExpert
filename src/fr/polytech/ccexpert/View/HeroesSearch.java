package fr.polytech.ccexpert.View;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
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

        back = new Command("Retour");
        setBackCommand(back);

        Toolbar toolbar = new Toolbar();
        setToolbar(toolbar);
        toolbar.setTitleComponent(toolbarContainer);
        toolbar.addCommandToSideMenu(back);

        searchButton.addActionListener(this);
        addCommandListener(this);
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
