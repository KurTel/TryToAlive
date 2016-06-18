package com.whailetrailhueil.trytoalive.managment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.javafx.collections.VetoableListDecorator;
import com.whailetrailhueil.trytoalive.helpers.Consts;
import com.whailetrailhueil.trytoalive.objects.world.GameWorld;
import com.whailetrailhueil.trytoalive.screens.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SuperRenderer {

    private GameScreen gameScreen;

    private List<DrawTools> listOfDrawTools;
    private DrawTools currentDrawTools;
    public DrawTools drawTools;

    //TODO попробовать однажды сделать шейдер для искажения эфира, закладка в фаерфоксе 2D GPU displacement mapping

    public SuperRenderer(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        drawTools = createDrawTools(gameScreen.getStandartWorldSize());

        drawToolsInitialization();
    }

    public void render(){
        Gdx.gl.glClearColor(1,1,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentDrawTools = listOfDrawTools.get(gameScreen.getCurrentWorldNumber());
        currentDrawTools.camera.update();
        currentDrawTools.update();

        currentDrawTools.batch.begin();
        gameScreen.getGameWorld().render(currentDrawTools);
        currentDrawTools.batch.end();
    };

    public DrawTools createDrawTools(Vector2 size){
        return new DrawTools(size);
    };

    public void drawToolsInitialization(){
        listOfDrawTools = new ArrayList<DrawTools>();
        listOfDrawTools.add(createDrawTools(gameScreen.getStandartWorldSize()));
        listOfDrawTools.add(createDrawTools(gameScreen.getStandartWorldSize()));
        listOfDrawTools.add(createDrawTools(gameScreen.getStandartWorldSize()));
        currentDrawTools = listOfDrawTools.get(0);
    }

    public void setVisibleWorld(GameWorld world, Vector2 screenSize){
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

        world.setVisibleWorld(visibleWorldLeftBottom,visibleWorldRightTop);
    }

    public DrawTools getDrawTools(){
        return currentDrawTools;
    }

    public void updateViewPortAndCamera(int width, int height) {
        for(Iterator<DrawTools> iter = listOfDrawTools.iterator(); iter.hasNext();){
            DrawTools drawTols = iter.next();
            drawTols.viewPort.update(width, height);
            drawTols.camera.position.set(Consts.WORLD_BIGGEST_SIDE / 2, Consts.WORLD_BIGGEST_SIDE /2 , 0);
        }
    }
}
