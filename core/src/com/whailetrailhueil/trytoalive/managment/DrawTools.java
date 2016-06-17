package com.whailetrailhueil.trytoalive.managment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.whailetrailhueil.trytoalive.helpers.Consts;

//в общем идея такова, что в каждом объекте мира мы будем сами определять какими инструментами
//будет происходить отрисовка
//экземпляр этого класса доступен в любом месте программы через статик функцию
//так же походу можно изменять настйроки камеры в любом месте кода
//скорее всего этого не надо делтаь в момент отрисовки, SpriteBatch

public class DrawTools {

    /**Singletone**/
    //private static  DrawTools instance = new DrawTools();

    //public          DrawTools getInstance(){return instance;};

    public          Viewport viewPort;
    public          OrthographicCamera camera;
    public          ShapeRenderer shapeRenderer;
    public          SpriteBatch batch;
    public          BitmapFont bitmapFont;

    //float           cameraStartScale = 1f;

    DrawTools(Vector2 size){
        //используется система с вьюпортом, в настоящее время fill вьюпорт
        //это значит, что камер будет подстраиваться так, чтобы захватывать мир по большей стороне экрана
        //камера создается без параметров, вьюпорт сам её подстраивает
        camera = new OrthographicCamera();

        //настройка вьюпорта под наш размер мира, точнее чтобы он охватывал 80% мира
        viewPort = new FillViewport(size.x, size.y, camera);
        viewPort.apply();

        //настраиваем позицию камеры, в данный момент по центру нашего мира
        camera.position.set(Consts.WORLD_BIGGEST_SIDE/2, Consts.WORLD_BIGGEST_SIDE/2, 0);
        camera.update();

        //на всякий случай выведем настройки камеры
        Gdx.app.log("DrawTools","viewpoert = " + viewPort.getScreenWidth() + " " + viewPort.getScreenHeight());
        Gdx.app.log("DrawTools","camera.viewpoert = " + camera.viewportWidth + " " + camera.viewportHeight);
        Gdx.app.log("DrawTools","camera.position = " + camera.position);
        Gdx.app.log("Drawtools","camera.zoom = " + camera.zoom);

        //создаём инструменты, которыми будем всё отрисовывать
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();

        //используем нашу камеру для отрисовки спрайтов и фигур
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
    }

    public void update(){
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
    }
}
