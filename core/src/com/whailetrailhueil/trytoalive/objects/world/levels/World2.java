package com.whailetrailhueil.trytoalive.objects.world.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.managment.DrawTools;
import com.whailetrailhueil.trytoalive.objects.gameObjects.EtherParticle;
import com.whailetrailhueil.trytoalive.objects.world.GameWorld;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 16.06.2016.
 */
public class World2 extends GameWorld {

    private List<EtherParticle> etherParticles;

    public World2(Vector2 size) {
        super(size);

        //создаём список частиц
        etherParticles = new ArrayList<EtherParticle>();

        //добавляем список частиц в список списков
        listOfAllTypesOfObject.add(etherParticles);

        //создаём частицу и добавляем её в список частиц
        EtherParticle etherParticle = new EtherParticle(this,new Vector2(500,500),150,0);
        etherParticles.add(etherParticle);
        myList.add(etherParticle);
    }

    private float timeToCheckWorldIsFull = 1f;
    private float deltaTime;

    @Override
    public void update(float delta){
        deltaTime += delta;
        if(deltaTime >= timeToCheckWorldIsFull){
            boolean isFull = checkWorldIsFull();
            Gdx.app.log("World2","isFull = " + isFull);
            deltaTime=0;
        }
        super.update(delta);
    };

    @Override
    public void render(DrawTools drawTools) {
        super.render(drawTools);
    }

    public boolean checkWorldIsFull(){
        //TODO делаем проверку для прохождения уровня. надо проверить достаточно ли много точек на экране, запихать всё в одну функцию было бы баще
        Vector2 visibleWorld = visibleWorldRightTop.cpy().sub(visibleWorldLeftBottom);
        Vector2 localCellRandomPosition;
        Vector2 globalCellRandomPosition;
        int counter = 0;
        for(int i=0;i<1000;i++){
            localCellRandomPosition = new Vector2(random.nextFloat()*visibleWorld.x, random.nextFloat()*visibleWorld.y);
            globalCellRandomPosition = localCellRandomPosition.add(visibleWorldLeftBottom);
            if(isInEtherParticles(globalCellRandomPosition.x,globalCellRandomPosition.y)) counter++;
        }
        Gdx.app.log("World2","counter = " + (float)counter/1000*100 + "%");
        if((float)counter/1000 > 0.8) return true;
        else return false;

    }

    public void createEtherParticleStandartSize(Vector2 position){
        float etherParticleStandartSize = 50f;
        EtherParticle etherParticle = new EtherParticle(this,position,etherParticleStandartSize,0);
        etherParticles.add(etherParticle);
    }

    public void createEtherParticleRandomSize(Vector2 position){
        float etherParticleRandomSize = random.nextFloat() * 80f + 20f;
        EtherParticle etherParticle = new EtherParticle(this,position,etherParticleRandomSize,0);
        etherParticles.add(etherParticle);
    }

    //проверяет, находятся ли координаты в области какойнибудь частицы
    public boolean isInEtherParticles(float x, float y){
        for(Iterator<EtherParticle> iter = etherParticles.iterator(); iter.hasNext();){
            EtherParticle object = iter.next();
            if(object.isPressed(x,y))
                return true;
        }
        return false;
    }

}
