package com.whailetrailhueil.trytoalive.managment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.screens.GameScreen;

/**
 * Created by User on 14.06.2016.
 */
public class DebugController implements InputProcessor {

    private GameScreen gameScreen;

    public DebugController(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }


    @Override
    public boolean keyDown(int keycode) {
        //Gdx.app.log("DebugController","pressedKey = " + keycode);
        switch(keycode){
            case(21):{
                Gdx.app.log("DebugController","leftPressed, go to previous level");
                gameScreen.changeToPreviousWorld();
                break;
            }
            case(22):{
                Gdx.app.log("DebugController","rightPressed, go to next level");
                gameScreen.changeToNextWorld();
                break;
            }
            case(62):{
                Gdx.app.log("DebugController","justSpace");
                gameScreen.changeToNextWorld();
                break;
            }
        }


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
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        gameScreen.getSuperRenderer().getDrawTools().camera.zoom += amount*0.1;
        return false;
    }
}
