package com.whailetrailhueil.trytoalive.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.whailetrailhueil.trytoalive.helpers.Consts;
import com.whailetrailhueil.trytoalive.managment.*;
import com.whailetrailhueil.trytoalive.objects.world.GameWorld;
import com.whailetrailhueil.trytoalive.objects.world.levels.World1;

public class GameScreen implements Screen {

    private FPSLogger fpsLogger;
    public float gameSpeed;

    private SuperRenderer superRenderer;

    //миксер из контроллеров, как для ГУИ так и для самой игры
    private InputMultiplexer multiplexer;

    private World1 gameWorld;
    private static Vector2 standartWorldSize = new Vector2(1000f,1000f);

    @Override
    public void show() {

        GameAssetManager.create();
        //загружаем наш атлас с текстурами
        GameAssetManager.getInstance().load("cosmos_atlas/cosmos.atlas", TextureAtlas.class);
        GameAssetManager.getInstance().load("ether/out/etherpar/etherParticle.atlas", TextureAtlas.class);
        //ждём пока атлас загрузится до конца, так как тут происходит ассинхронная загрузка вместе с выполнением кода
        GameAssetManager.getInstance().finishLoading();

        //gameAssetManager = new GameAssetManager();
        //GameAssetManager.setOurInstance(gameAssetManager);

        //собственно создаём мир, в котором будут наши объекты, вся механика по сути тоже должна происходить здесь
        //TODO скорее всего миры это и будут уровни, и придётся в гейм скрине следить за тем какой уровень был последний и создавать уровни при прохождении
        gameWorld = new World1(standartWorldSize);

        //в этом классе происходит отрисовка объектов и настройка всех инструментов
        superRenderer = new SuperRenderer(this);

        //в этом классе происходит обработка нажатий и ваще всех инпут процессов
        //сначала добавляем контроллер интерфейса, если он выкинет false, то управление передвется следующему контроллеру
        //TODO так как в каждом уровне и мире соответственно будут свои герои, и задачи и логика, то для каждого мира будет свой инпут процессор
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new GestureDetector(new GameGUIController(this)));
        multiplexer.addProcessor((new GameWorldController(this)));
        Gdx.input.setInputProcessor(multiplexer);

        //TODO ГЛАВНОЕ СЕЙЧАС, НАПИСАТЬ ПЕРВЫЙ УРОВЕНЬ!!!

        fpsLogger = new FPSLogger();
        gameSpeed = 1;
    }

    @Override
    public void render(float delta) {

        gameWorld.update(delta*gameSpeed);

        superRenderer.render();

//        Gdx.app.log("GameScreen","viewpoert = " + superRenderer.getDrawTools().viewPort.getScreenWidth() + " " + superRenderer.getDrawTools().viewPort.getScreenHeight());
//        Gdx.app.log("GameScreen","viewpoert2 = " + superRenderer.getDrawTools().viewPort.getWorldWidth() + " " + superRenderer.getDrawTools().viewPort.getWorldHeight());
//        Gdx.app.log("GameScreen","viewpoert2 = " + superRenderer.getDrawTools().viewPort.getWorldWidth() + " " + superRenderer.getDrawTools().viewPort.getWorldHeight());
//
//        Gdx.app.log("GameScreen","camera.viewpoert = " + superRenderer.getDrawTools().camera.viewportWidth + " " + superRenderer.getDrawTools().camera.viewportHeight);
//        Gdx.app.log("GameScreen","camera.position = " + superRenderer.getDrawTools().camera.position);
//        Gdx.app.log("GameScreen","camera.zoom = " + superRenderer.getDrawTools().camera.zoom);

        //fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen","camera.position = " + superRenderer.drawTools.camera.position);

        //эти строки нужны для того, чтобы филл вьюпорт работал нормально + камера сразу становилась в центр мира
        superRenderer.getDrawTools().viewPort.update(width, height);
        superRenderer.getDrawTools().camera.position.set(Consts.WORLD_BIGGEST_SIDE / 2, Consts.WORLD_BIGGEST_SIDE /2 , 0);
        superRenderer.setVisibleWorld(new Vector2(width,height));
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen","pause");
    }

    @Override
    public void resume() {
        GameAssetManager.getInstance().finishLoading();
        Gdx.app.log("GameScreen","resume");

    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen","hide");
        dispose();
    }

    @Override
    public void dispose() {
        Gdx.app.log("GameScreen","dispose");
        GameAssetManager.getInstance().dispose();

    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public SuperRenderer getSuperRenderer() {
        return superRenderer;
    }

    public InputMultiplexer getMultiplexer() {
        return multiplexer;
    }

    public Vector2 getStandartWorldSize(){
        return standartWorldSize;
    }
}
