package com.whailetrailhueil.trytoalive.objects.gui.buttons;

import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.objects.AbstractObject;

/**
 * Created by User on 15.06.2016.
 */
public class RoundButton extends Button {

    public RoundButton (AbstractObject parent, Vector2 center, float sizeX, float rotation, String nameOfTexture){
        super(parent, center, sizeX, rotation, nameOfTexture);
    }

    @Override
    public boolean isPressed(float x, float y) {
        return getCircle().contains(x,y);
    }
}
