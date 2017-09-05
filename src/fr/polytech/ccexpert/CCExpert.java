package fr.polytech.ccexpert;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import fr.polytech.ccexpert.view.*;

import java.io.IOException;

public class CCExpert {
    private Form current;
    private Resources theme;

    private Home home;
    private HeroesSearch heroesSearch;
    private GuildWar guildWar;
    private Dungeons dungeons;
    private FeaturesToCome toCome;
    private Simulators simulators;

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
        dungeons = new Dungeons(this);
        toCome = new FeaturesToCome(this);
        simulators = new Simulators(this);
        guildWar = new GuildWar(this);

        setToolbar(home.getToolbar());

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

    public void loadFeatToCome() {
        toCome.show();
    }

    public void loadSimulators() {
        simulators.show();
    }

    public Resources getTheme() {
        return theme;
    }

    public Toolbar setToolbar(Toolbar tb) {
        Image icon = theme.getImage("unicorn.jpg");
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("CCExpert Menu", "SidemenuTag"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, e -> home.show());
        tb.addMaterialCommandToSideMenu("Héros", FontImage.MATERIAL_WEB, e -> heroesSearch.show());
        tb.addMaterialCommandToSideMenu("Simulateurs", FontImage.MATERIAL_SETTINGS, e -> simulators.show());
        tb.addMaterialCommandToSideMenu("Donjons", FontImage.MATERIAL_INFO, e -> dungeons.show());
        tb.addMaterialCommandToSideMenu("Fonctionnalités à venir", FontImage.MATERIAL_INFO, e -> toCome.show());
        return tb;
    }
}
