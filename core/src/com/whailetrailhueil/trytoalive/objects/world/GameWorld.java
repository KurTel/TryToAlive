package com.whailetrailhueil.trytoalive.objects.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.managment.DrawTools;
import com.whailetrailhueil.trytoalive.objects.AbstractObject;
import com.whailetrailhueil.trytoalive.objects.gameobjects.EtherParticle;
import com.whailetrailhueil.trytoalive.objects.gameobjects.Star;
import com.whailetrailhueil.trytoalive.screens.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
//TODO короче такая тема, невозможно создав GameWorld сразу установить ввидимые границы
//поэтому тут два варианта, либо изнохратиться и умудриться всё же их установить и создавать обхекты в конструкторе
//либо установить позже и создавать обхекты в рендерере
//либо установить позжи ваще забить на всё и просто не создавать обхекты дальше чем 280 от центра
//280 это, 1080/1920 = 0.5625, 1000*0.5625 = 562.5, 562.5/2 = 281,25 в каждую сторону, такое будет расстояние от центра при разрешении 16/9
public class GameWorld extends AbstractObject {

    protected static Random random = new Random();

    protected Vector2 visibleWorldLeftBottom,visibleWorldRightTop;
    protected Vector2 visibleWorld;

    //список всех списков всех объектов
    protected List<List<? extends AbstractObject>> listOfAllTypesOfObject;
    //список частиц эфира


    public GameWorld(Vector2 size) {
        super();
        this.size = size;

        //создаём список всех списков
        listOfAllTypesOfObject = new ArrayList<List<? extends AbstractObject>>();
    }

    @Override
    public void update(float delta){
        updateAllObjects(delta);
    }

    @Override
    public void render(DrawTools drawTools) {
        renderAllObjects(drawTools);
    }

    public void setVisibleWorld(Vector2 visibleWorldLeftBottom, Vector2 visibleWorldRightTop){
        this.visibleWorldLeftBottom = visibleWorldLeftBottom;
        this.visibleWorldRightTop = visibleWorldRightTop;
        visibleWorld = visibleWorldRightTop.sub(visibleWorldLeftBottom);
    };

    //супер апдейт на все возможные объекты в мире
    protected void updateAllObjects(float delta){
        for(Iterator<List<? extends AbstractObject>> iter = listOfAllTypesOfObject.iterator(); iter.hasNext();){
            List<? extends AbstractObject> list = iter.next();
            update(list,delta);
        }
    };

    //супер рендер на все возможные объекты в мире
    protected void renderAllObjects(DrawTools drawTools){
        for(Iterator<List<? extends AbstractObject>> iter = listOfAllTypesOfObject.iterator(); iter.hasNext();){
            List<? extends AbstractObject> list = iter.next();
            render(list,drawTools);
        }
    };

    //универсальная функция рендера которая проходится по тому списrу, который ему подсунули и поизводит апдейт
    protected void update(List<? extends AbstractObject> list, float delta){
        for(Iterator<? extends AbstractObject> iter = list.iterator(); iter.hasNext();){
            AbstractObject object = iter.next();
            object.update(delta);
        }
    };

    //аналогичная ситуация, только тут происходит рендер
    protected void render(List<? extends AbstractObject> list, DrawTools drawTools){
        for(Iterator<? extends AbstractObject> iter = list.iterator(); iter.hasNext();){
            AbstractObject object = iter.next();
            object.render(drawTools);
        }
    };

}
