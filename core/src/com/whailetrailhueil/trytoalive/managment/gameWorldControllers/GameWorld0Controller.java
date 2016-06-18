package com.whailetrailhueil.trytoalive.managment.gameWorldControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.whailetrailhueil.trytoalive.managment.GameWorldController;
import com.whailetrailhueil.trytoalive.objects.world.levels.World0;
import com.whailetrailhueil.trytoalive.objects.world.levels.World1;
import com.whailetrailhueil.trytoalive.screens.GameScreen;

/**
 * Created by User on 27.05.2016.
 */
public class GameWorld0Controller extends GameWorldController {

    private World0 world;

    public GameWorld0Controller(GameScreen gameScreen) {
        super(gameScreen);
        this.world = (World0)gameScreen.getGameWorldByNumber(0);
    }
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Vector3 coord2 = gameScreen.getSuperRenderer().getDrawTools().viewPort.unproject(new Vector3(x,y,0));
        Vector2 coord = new Vector2(coord2.x,coord2.y);
        //Gdx.app.log("GameWorld0Controller","coord = " + coord);
        if(!world.isInEtherParticles(coord.x,coord.y))
            world.createEtherParticleRandomSize(coord);
        Gdx.app.log("GameWorld0Controller","there");
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
