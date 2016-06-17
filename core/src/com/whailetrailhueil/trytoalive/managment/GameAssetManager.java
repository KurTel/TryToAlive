package com.whailetrailhueil.trytoalive.managment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by User on 14.06.2016.
 */
public class GameAssetManager extends AssetManager {
    private static GameAssetManager ourInstance = new GameAssetManager();

    public static GameAssetManager getInstance() {
        return ourInstance;
    }

    public GameAssetManager() {
        super();
        Texture.setAssetManager(this);
    }

    public static void setOurInstance(GameAssetManager gameAssetManager){
        ourInstance = gameAssetManager;
    }

    @Override
    public void dispose() {
        Gdx.app.log("GameAssetManager","disposeStart");
        super.dispose();
        ourInstance = null;
        Gdx.app.log("GameAssetManager","dispose");
    }

    public static void create() {
        if(ourInstance == null){
            ourInstance = new GameAssetManager();
        }
    }
}
