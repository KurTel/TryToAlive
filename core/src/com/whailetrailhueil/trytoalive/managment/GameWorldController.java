package com.whailetrailhueil.trytoalive.managment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.whailetrailhueil.trytoalive.objects.world.GameWorld;
import com.whailetrailhueil.trytoalive.objects.world.levels.World1;
import com.whailetrailhueil.trytoalive.screens.GameScreen;

/**
 * Created by User on 27.05.2016.
 */
public class GameWorldController implements InputProcessor {

    private GameScreen gameScreen;
    private World1 world;

    public GameWorldController(GameScreen gameScreen) {

        this.gameScreen = gameScreen;
        this.world = (World1)gameScreen.getGameWorld();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 coord2 = gameScreen.getSuperRenderer().getDrawTools().viewPort.unproject(new Vector3(screenX,screenY,0));
        Vector2 coord = new Vector2(coord2.x,coord2.y);
        //Gdx.app.log("GameWorldController","coord = " + coord);
        if(!world.isInEtherParticles(coord.x,coord.y))
            world.createEtherParticleRandomSize(coord);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        //gameScreen.getSuperRenderer().getDrawTools().camera.zoom += amount*0.1;
        gameScreen.gameSpeed -= amount*0.1;
        return false;
    }
}
