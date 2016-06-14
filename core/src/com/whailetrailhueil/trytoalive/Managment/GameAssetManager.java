package com.whailetrailhueil.trytoalive.Managment;

import com.badlogic.gdx.assets.AssetManager;

/**
 * Created by art
 */
public class GameAssetManager extends AssetManager {
    private static GameAssetManager ourInstance = new GameAssetManager();

    public static GameAssetManager getInstance() {
        return ourInstance;
    }

    private GameAssetManager() {
    }

    @Override
    public void dispose() {
        ourInstance.dispose();
        ourInstance = null;
    }
}
