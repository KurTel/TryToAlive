package com.whailetrailhueil.trytoalive.objects.gui.buttons;

import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.objects.AbstractObject;

/**
 * Created by User on 15.06.2016.
 */
public class Button extends AbstractObject {

    public Button (AbstractObject parent, Vector2 center, float sizeX, float rotation, String nameOfTexture){
        super(parent, center, sizeX, rotation, nameOfTexture);
    }

    public boolean isPressed(float x, float y){
        return false;
    }

}
