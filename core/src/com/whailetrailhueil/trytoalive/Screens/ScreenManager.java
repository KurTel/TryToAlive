package com.whailetrailhueil.trytoalive.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by User on 26.05.2016.
 */
//Этот класс взят с интернета, в него ничего не добавлено, он стандартный типа
//Он управляет эранами в нашей игре
//Имеет своё состояние, которое можно получить через статик функцию, его не надо никуда передвать
//Поэтому переключить экраны можно в любом месте игры
public class ScreenManager {
    private Game game;
    private IntMap<Screen> screens;

    /** Singelton **/
    private static ScreenManager ourInstance = new ScreenManager();

    public static ScreenManager getInstance() {return ourInstance;}

    private ScreenManager() {screens = new IntMap<Screen>();}

    public void init(Game game) {
        this.game = game;
    }

    public void show(CustomScreen screen) {
        if (game == null) return;
        if (!screens.containsKey(screen.ordinal())) {
            screens.put(screen.ordinal(), screen.getScreenInstance());
        }
        game.setScreen(screens.get(screen.ordinal()));
    }

    public void dispose(CustomScreen screen) {
        if (!screens.containsKey(screen.ordinal())) return;
        screens.remove(screen.ordinal()).dispose();
    }

    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
        screens.clear();
        ourInstance = null;
    }
}
