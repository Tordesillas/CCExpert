package fr.polytech.ccexpert.model;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Display;
import fr.polytech.ccexpert.CCExpert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseBrowser {
    private CCExpert main;
    private Database db;
    private Sets sets;

    public DatabaseBrowser(CCExpert main, String path) throws IOException {
        this.main = main;
        sets = new Sets();

        loadDatabase(path);
        loadHeroes();
        //loadDungeons();

        db.close();
    }

    private void loadDatabase(String path) throws IOException {
        db = Database.openOrCreate(path);
        if (Storage.getInstance().readObject("initialized") == null) {
            OutputStream o = FileSystemStorage.getInstance().openOutputStream(Database.getDatabasePath(path));
            InputStream i = Display.getInstance().getResourceAsStream(getClass(), "/" + path);
            Util.copy(i, o);
            Util.cleanup(o);
            Util.cleanup(i);

            db.close();
            db = Database.openOrCreate(path);
            Storage.getInstance().writeObject("initalized", "true");
        }
    }

    private void loadHeroes() throws IOException {
        Cursor c = db.executeQuery("select * from heroes");
        Row l;
        Hero hero;
        while (c.next()) {
            l = c.getRow();
            hero = new Hero(l.getString(1), l.getString(2), main.getTheme().getImage(l.getString(3)),
                    l.getInteger(4), l.getInteger(5), l.getInteger(6), l.getInteger(7), l.getInteger(8),
                    l.getInteger(9), l.getInteger(10), l.getInteger(11), l.getInteger(12),l.getInteger(13),
                    l.getInteger(14), l.getInteger(15),l.getInteger(16));
                    sets.addHero(hero, l.getInteger(0));
        }
        c.close();
    }

    private void loadDungeons() throws IOException {
        Cursor c = db.executeQuery("select * from Dungeons");
        Row line;
        while (c.next()) {
            line = c.getRow();
            System.out.println(line.getString(0));
        }
        c.close();
    }

    public Sets getSetsFromDatabase() {
        return sets;
    }
}
