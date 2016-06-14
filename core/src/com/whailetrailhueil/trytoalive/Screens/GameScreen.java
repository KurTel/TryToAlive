package com.whailetrailhueil.trytoalive.Screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.EntityComponents.MovementComponent;
import com.whailetrailhueil.trytoalive.EntityComponents.PositionComponent;
import com.whailetrailhueil.trytoalive.EntityComponents.RenderComponent;
import com.whailetrailhueil.trytoalive.EntitySystems.MovementSystem;
import com.whailetrailhueil.trytoalive.EntitySystems.RenderSystem;
import com.whailetrailhueil.trytoalive.Managment.GameAssetManager;

/**
 * Created by User on 26.05.2016.
 */
public class GameScreen implements Screen {

    private Engine engine;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    @Override
    public void show() {
        GameAssetManager.getInstance().load("cosmos_atlas/cosmos.atlas", TextureAtlas.class);
        GameAssetManager.getInstance().finishLoading();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        this.batch = new SpriteBatch();
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);

        engine = new Engine();

        engine.addSystem(new MovementSystem());
        engine.addSystem(new RenderSystem(camera));

        Entity entity = new Entity();
        entity.add(new PositionComponent(new Vector2(100,100)));
        entity.add(new MovementComponent(10, 10));
        entity.add(new RenderComponent(new Vector2()));

        engine.addEntity(entity);
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
