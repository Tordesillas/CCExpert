package fr.polytech.ccexpert;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import fr.polytech.ccexpert.view.Dungeons;
import fr.polytech.ccexpert.view.GuildWar;
import fr.polytech.ccexpert.view.HeroesSearch;
import fr.polytech.ccexpert.view.Home;

import java.io.IOException;

public class CCExpert {
    private Form current;
    private Resources theme;

    private Home home;
    private HeroesSearch heroesSearch;
    private GuildWar guildWar;
    private Dungeons dungeons;

    public void init(Object context) {
        Toolbar.setGlobalToolbar(true);

        try {
            theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }

        home = new Home(this);
        heroesSearch = new HeroesSearch(this);
        guildWar = new GuildWar(this);
        dungeons = new Dungeons(this);

        initializeToolbar();

        home.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() { }

    public void loadHome() {
        home.show();
    }

    public void loadHeroes() {
        heroesSearch.show();
    }

    public void loadGuildWar() {
        guildWar.show();
    }

    public void loadDungeons() {
        dungeons.show();
    }

    private void initializeToolbar() {
        Toolbar toolbar = home.getToolbar();
        Image icon = theme.getImage("unicorn.jpg");
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("CCExpert Menu", "SidemenuTag"));
        topBar.setUIID("SideCommand");
        toolbar.addComponentToSideMenu(topBar);

        toolbar.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, e -> home.show());
        toolbar.addMaterialCommandToSideMenu("Héros", FontImage.MATERIAL_WEB, e -> heroesSearch.show());
        toolbar.addMaterialCommandToSideMenu("Guerre de Guilde", FontImage.MATERIAL_SETTINGS, e -> guildWar.show());
        toolbar.addMaterialCommandToSideMenu("Donjons", FontImage.MATERIAL_INFO, e -> dungeons.show());
    }

    public Resources getTheme() {
        return theme;
    }
}
