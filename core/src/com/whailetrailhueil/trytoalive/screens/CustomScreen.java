package com.whailetrailhueil.trytoalive.screens;

import com.badlogic.gdx.Screen;

/**
 * Created by User on 14.06.2016.
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
