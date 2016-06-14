package com.whailetrailhueil.trytoalive.EntityComponents;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.whailetrailhueil.trytoalive.Managment.GameAssetManager;

/**
 * Created by User on 10.06.2016.
 */
public class RenderComponent implements Component, Json.Serializable {

    private TextureRegion frame = GameAssetManager.getInstance().get("cosmos_atlas/cosmos.atlas", TextureAtlas.class).findRegion("circle");
    //private TextureRegion frame = GameAssetManager.getInstance().
    //TextureAtlas atlas = GameAssetManager.getInstance().get("cosmos.atlas", TextureAtlas.class);


    private Vector2 frameOffset = new Vector2();

    public RenderComponent(){}

    public RenderComponent(Vector2 offset){

        this.frameOffset = offset;
    }
    public RenderComponent(TextureRegion texture, Vector2 offset){
        this.frame = texture;
        this.frameOffset = offset;
    }

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        json.readField(this, "frameOffset", jsonData);
    }

    public TextureRegion getFrame() {
        return frame;
    }
}