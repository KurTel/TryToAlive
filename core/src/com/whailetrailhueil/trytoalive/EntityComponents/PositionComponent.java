package com.whailetrailhueil.trytoalive.EntityComponents;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by User on 10.06.2016.
 */
public class PositionComponent implements Component, Json.Serializable {

    private Vector2 position;

    public PositionComponent(){
        position = new Vector2();
    }

    public PositionComponent(Vector2 position){
        this.position = position;
    }

    @Override
    public void write(Json json) {}

    @Override
    public void read(Json json, JsonValue jsonData) {
        json.readField(this, "position", jsonData);
    }

    public Vector2 getPosition() {
        return position;
    }
}
