package com.whailetrailhueil.trytoalive.managment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.javafx.collections.VetoableListDecorator;
import com.whailetrailhueil.trytoalive.screens.GameScreen;

public class SuperRenderer {

    private GameScreen gameScreen;

    public DrawTools drawTools;

    //TODO попробовать однажды сделать шейдер для искажения эфира, закладка в фаерфоксе 2D GPU displacement mapping

    public SuperRenderer(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        drawTools = createDrawTools(gameScreen.getStandartWorldSize());
    }

    public void render(){
        Gdx.gl.glClearColor(1,1,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawTools.camera.update();
        drawTools.update();

        drawTools.batch.begin();
        gameScreen.getGameWorld().render(drawTools);
        drawTools.batch.end();
    };

    public DrawTools createDrawTools(Vector2 size){
        return new DrawTools(size);
    };

    public void setVisibleWorld(Vector2 screenSize){
        Gdx.app.log("AllWorld","screensize = " + screenSize);
        float aspectRatio = screenSize.x/screenSize.y;
        Vector2 visibleWorld;
        if(aspectRatio>=1)
            visibleWorld = new Vector2(drawTools.viewPort.getWorldWidth(), drawTools.viewPort.getWorldHeight()/aspectRatio);
        else
            visibleWorld = new Vector2(drawTools.viewPort.getWorldWidth()*aspectRatio, drawTools.viewPort.getWorldHeight());

        Gdx.app.log("SuperRenderer","visibleWorld = " + visibleWorld);

        Vector2 visibleWorldLeftBottom = new Vector2(   (drawTools.viewPort.getWorldWidth()-visibleWorld.x)/2,
                                                        (drawTools.viewPort.getWorldHeight()-visibleWorld.y)/2);
        Vector2 visibleWorldRightTop = new Vector2( drawTools.viewPort.getWorldWidth() - (drawTools.viewPort.getWorldWidth()-visibleWorld.x)/2,
                                                    drawTools.viewPort.getWorldHeight() - (drawTools.viewPort.getWorldHeight()-visibleWorld.y)/2);
        Gdx.app.log("SuperRenderer","visibleWorldLeftBottom = " + visibleWorldLeftBottom);
        Gdx.app.log("SuperRenderer","visibleWorldRightTop = " + visibleWorldRightTop);

        gameScreen.getGameWorld().setVisibleWorld(visibleWorldLeftBottom,visibleWorldRightTop);
    }

    public DrawTools getDrawTools(){
        return drawTools;
    }
}
