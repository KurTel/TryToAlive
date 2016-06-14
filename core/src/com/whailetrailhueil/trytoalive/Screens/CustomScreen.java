package com.whailetrailhueil.trytoalive.Screens;

import com.badlogic.gdx.Screen;

/**
 * Created by User on 26.05.2016.
 */
public enum CustomScreen {
    GAME_SCREEN {
        @Override
        protected Screen getScreenInstance() {
            return new GameScreen();
        }
    };

    protected abstract Screen getScreenInstance();
}
