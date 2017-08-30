package fr.polytech.ccexpert;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import fr.polytech.ccexpert.View.HeroesSearch;
import fr.polytech.ccexpert.View.Home;

import java.io.IOException;


public class CCExpert {
    private Form current;
    private Resources theme;
    private Home home;
    private HeroesSearch heroesSearch;

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

        Toolbar tb = home.getToolbar();
        Image icon = theme.getImage("unicorn.jpg");
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("Miaou", "SidemenuTag"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {});
        tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {});
        tb.addMaterialCommandToSideMenu("Settings", FontImage.MATERIAL_SETTINGS, e -> {});
        tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {});

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

    public void loadGdG() {

    }

    public void loadDonjons() {

    }

    public Resources getTheme() {
        return theme;
    }
}
