package com.whailetrailhueil.trytoalive.managment.gameWorldControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.whailetrailhueil.trytoalive.managment.GameWorldController;
import com.whailetrailhueil.trytoalive.objects.world.levels.World0;
import com.whailetrailhueil.trytoalive.objects.world.levels.World1;
import com.whailetrailhueil.trytoalive.screens.GameScreen;

/**
 * Created by User on 27.05.2016.
 */
public class GameWorld1Controller extends GameWorldController {

    private World1 world;

    public GameWorld1Controller(GameScreen gameScreen) {
        super(gameScreen);
        this.world = (World1)gameScreen.getGameWorldByNumber(1);
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
        float sizeBound = 100;
        if(!world.isInEtherParticles(coord.x,coord.y))
            world.createEtherParticleRandomSize(coord,sizeBound);

        Gdx.app.log("GameWorld1Controller","there");
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
