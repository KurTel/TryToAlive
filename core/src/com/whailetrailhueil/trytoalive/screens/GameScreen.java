package com.whailetrailhueil.trytoalive.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.helpers.Consts;
import com.whailetrailhueil.trytoalive.managment.*;
import com.whailetrailhueil.trytoalive.managment.gameWorldControllers.GameWorld0Controller;
import com.whailetrailhueil.trytoalive.managment.gameWorldControllers.GameWorld1Controller;
import com.whailetrailhueil.trytoalive.managment.gameWorldControllers.GameWorld2Controller;
import com.whailetrailhueil.trytoalive.objects.world.GameWorld;
import com.whailetrailhueil.trytoalive.objects.world.levels.World0;
import com.whailetrailhueil.trytoalive.objects.world.levels.World1;
import com.whailetrailhueil.trytoalive.objects.world.levels.World2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameScreen implements Screen {

    private FPSLogger fpsLogger;
    public float gameSpeed;

    //то, что отвечает за отрисовку
    private SuperRenderer superRenderer;

    //миксер из контроллеров, как для ГУИ так и для самой игры
    private InputMultiplexer multiplexer;

    private List<GameGUIController> listOfGUIControllers;
    private List<GameWorldController> listOfWorldControllers;
    private GameGUIController currentGUIController;
    private GameWorldController currentWorldController;

    private DebugController debugController;

    private List<GameWorld> listOfWorlds;
    private GameWorld currentWorld;
    private int currentWorldNumber;
    private static Vector2 standartWorldSize = new Vector2(1000f,1000f);

    @Override
    public void show() {

        //инициализация АссетМенеджера
        GameAssetManager.create();

        //было бы неплохо загружать шрифты в ассет менеджер, а оптом из него
        FileHandleResolver resolver = new InternalFileHandleResolver();
        GameAssetManager.getInstance().setLoader(FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(resolver));
        GameAssetManager.getInstance().setLoader(BitmapFont.class, ".ttf",
                new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter size1Params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        size1Params.fontFileName = "fonts/GIGI.ttf";
        size1Params.fontParameters.size = 100;
        size1Params.fontParameters.color = Color.BLACK;
        size1Params.fontParameters.magFilter = Texture.TextureFilter.Linear;
        size1Params.fontParameters.minFilter = Texture.TextureFilter.Linear;
        GameAssetManager.getInstance().load("size10.ttf", BitmapFont.class, size1Params);
        //GameAssetManager.getInstance().load("fonts/arial.ttf", BitmapFont.class);


        //загружаем наш атлас с текстурами
        GameAssetManager.getInstance().load("cosmos_atlas/cosmos.atlas", TextureAtlas.class);
        GameAssetManager.getInstance().load("ether/out/etherpar/etherParticle.atlas", TextureAtlas.class);
        //TODO здесь могуть быть проблемы
        //GameAssetManager.getInstance().load("fonts/GIGI.ttf",FreeTypeFontGenerator.class);
        //ждём пока атлас загрузится до конца, так как тут происходит ассинхронная загрузка вместе с выполнением кода
        GameAssetManager.getInstance().finishLoading();

        //собственно создаём мир, в котором будут наши объекты, вся механика по сути тоже должна происходить здесь
        //TODO скорее всего миры это и будут уровни, и придётся в гейм скрине следить за тем какой уровень был последний и создавать уровни при прохождении

        //номер текущего уровня
        currentWorldNumber = 0;

        //список уровней, добавление уровня, установка текущего уровня
        listOfWorlds = new ArrayList<GameWorld>();
        listOfWorlds.add(new World0(standartWorldSize));
        listOfWorlds.add(new World1(standartWorldSize));
        listOfWorlds.add(new World2(standartWorldSize));
        currentWorld = listOfWorlds.get(currentWorldNumber);

        //в этом классе происходит отрисовка объектов и настройка всех инструментов
        //этот экземпляр один, но внутри него есть DrawTools, который у каждого уровня свой
        superRenderer = new SuperRenderer(this);

        //в этом классе происходит обработка нажатий и ваще всех инпут процессов
        //сначала добавляем контроллер интерфейса, если он выкинет false, то управление передвется следующему контроллеру
        //TODO так как в каждом уровне и мире соответственно будут свои герои, и задачи и логика, то для каждого мира будет свой инпут процессор

        //контроллер с помощью которого можно управлять миром напохер
        debugController = new DebugController(this);

        //инициализация списка контроллеров интерфейса, добавление контроллеров
        GUIControllerInitialization();
        currentGUIController = listOfGUIControllers.get(currentWorldNumber);

        //инициализация списка контроллеров мира, добавление контроллеров
        WorldControllerInitialization();
        currentWorldController = listOfWorldControllers.get(currentWorldNumber);

        //инициализация мультиплексора и добавление в него контроллеров текущего мира
        multiplexer = new InputMultiplexer();
        updateInputProcessor();

        //TODO ГЛАВНОЕ СЕЙЧАС, НАПИСАТЬ ПЕРВЫЙ УРОВЕНЬ!!!

        fpsLogger = new FPSLogger();
        gameSpeed = 1;
    }

    @Override
    public void render(float delta) {

        currentWorld.update(delta*gameSpeed);

        superRenderer.render();

        //fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen","camera.position = " + superRenderer.drawTools.camera.position);

        superRenderer.updateViewPortAndCamera(width, height);
        //эти строки нужны для того, чтобы филл вьюпорт работал нормально + камера сразу становилась в центр мира
//        for(Iterator<DrawTools> iter = superRenderer.getDrawTools().iterator(); iter.hasNext();){
//            GameWorld world = iter.next();
//            superRenderer.setVisibleWorld(world,new Vector2(width,height));
//        }
//        superRenderer.getDrawTools().viewPort.update(width, height);
//        superRenderer.getDrawTools().camera.position.set(Consts.WORLD_BIGGEST_SIDE / 2, Consts.WORLD_BIGGEST_SIDE /2 , 0);
        for(Iterator<GameWorld> iter = listOfWorlds.iterator(); iter.hasNext();){
            GameWorld world = iter.next();
            superRenderer.setVisibleWorld(world,new Vector2(width,height));
        }
        //1superRenderer.setVisibleWorldInAllWorlds(new Vector2(width,height));
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

    public void changeToNextWorld(){
        changeWorld(currentWorldNumber+1);
    }

    public void changeToPreviousWorld(){
        changeWorld(currentWorldNumber-1);
    }

    public void changeWorld(int worldNumber){
        Gdx.app.log("GameScreen","worldNumber = " + worldNumber);
        if( worldNumber >= 0 && worldNumber < listOfWorlds.size() ){
            currentWorld = listOfWorlds.get(worldNumber);
            currentWorldNumber = worldNumber;
            updateInputProcessor();
        }
    }

    public GameWorld getGameWorldByNumber(int worldNumber){
        if( worldNumber >= 0 && worldNumber < listOfWorlds.size()){
            return listOfWorlds.get(worldNumber);
        }
        return null;
    }

    private void updateInputProcessor(){
        multiplexer.clear();

        multiplexer.addProcessor(debugController);

        currentGUIController = listOfGUIControllers.get(currentWorldNumber);
        multiplexer.addProcessor(new GestureDetector(currentGUIController));

        currentWorldController = listOfWorldControllers.get(currentWorldNumber);
        multiplexer.addProcessor(new GestureDetector(currentWorldController));

        Gdx.input.setInputProcessor(multiplexer);
    }

    private void GUIControllerInitialization(){
        listOfGUIControllers = new ArrayList<GameGUIController>();
        listOfGUIControllers.add(new GameGUIController(this));
        listOfGUIControllers.add(new GameGUIController(this));
        listOfGUIControllers.add(new GameGUIController(this));
    }

    private void WorldControllerInitialization(){
        listOfWorldControllers = new ArrayList<GameWorldController>();
        listOfWorldControllers.add(new GameWorld0Controller(this));
        listOfWorldControllers.add(new GameWorld1Controller(this));
        listOfWorldControllers.add(new GameWorld2Controller(this));
    }

    public int getCurrentWorldNumber(){
        return currentWorldNumber;
    }

    public GameWorld getGameWorld() {
        return currentWorld;
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
