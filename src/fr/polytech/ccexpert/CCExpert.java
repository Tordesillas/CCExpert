package fr.polytech.ccexpert;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import fr.polytech.ccexpert.model.DatabaseBrowser;
import fr.polytech.ccexpert.model.Sets;
import fr.polytech.ccexpert.view.*;
import fr.polytech.ccexpert.view.dungeon.Dungeons;
import fr.polytech.ccexpert.view.hero.HeroesSearch;
import fr.polytech.ccexpert.view.simulator.*;

import java.io.IOException;

public class CCExpert {
    private Form current;
    private Resources theme;
    private Sets sets;

    private Home home;
    private HeroesSearch heroesSearch;
    private GuildWar guildWar;
    private Dungeons dungeons;
    private FeaturesToCome toCome;
    private Simulators simulators;
    private ShardSimulator shardSimulator;
    private CrystalSimulator crystalSimulator;
    private DodgeSimulator dodgeSimulator;
    private AccuracySimulator accuracySimulator;
    private AttackSpeedSimulator attackSpeedSimulator;
    private AetherockSimulator aetherockSimulator;

    public void init(Object context) {
        Toolbar.setGlobalToolbar(true);

        try {
            theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme("B&O"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }

        DatabaseBrowser db;
        try {
            db = new DatabaseBrowser(this, "ccexpert.sqlite");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        sets = db.getSetsFromDatabase();

        home = new Home(this);
        heroesSearch = new HeroesSearch(this);
        dungeons = new Dungeons(this);
        toCome = new FeaturesToCome(this);
        simulators = new Simulators(this);
        guildWar = new GuildWar(this);
        shardSimulator = new ShardSimulator(this);
        crystalSimulator = new CrystalSimulator(this);
        dodgeSimulator = new DodgeSimulator(this);
        accuracySimulator = new AccuracySimulator(this);
        attackSpeedSimulator = new AttackSpeedSimulator(this);
        aetherockSimulator = new AetherockSimulator(this);

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

    public void loadShardSimulator() {
        shardSimulator.show();
    }

    public void loadCrystalSimulator() {
        crystalSimulator.show();
    }

    public void loadDodgeSimulator() {
        dodgeSimulator.show();
    }

    public void loadAccuracySimulator() {
        accuracySimulator.show();
    }

    public void loadAttackSpeedSimulator() {
        attackSpeedSimulator.show();
    }

    public void loadAetherocksSimulator() {
        aetherockSimulator.show();
    }

    public Toolbar setToolbar(Toolbar tb) {
        Image icon = theme.getImage("unicorn.jpg");
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("CCExpert Menu", "SidemenuTag"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, e -> home.show());
        tb.addMaterialCommandToSideMenu("Héros", FontImage.MATERIAL_PERM_IDENTITY, e -> heroesSearch.show());
        tb.addMaterialCommandToSideMenu("Simulateurs", FontImage.MATERIAL_BUILD, e -> simulators.show());
        tb.addMaterialCommandToSideMenu("Donjons", FontImage.MATERIAL_GAMEPAD, e -> dungeons.show());
        tb.addMaterialCommandToSideMenu("En savoir plus", FontImage.MATERIAL_INFO, e -> toCome.show());
        return tb;
    }

    public Resources getTheme() {
        return theme;
    }

    public Sets getSets() {
        return sets;
    }
}
