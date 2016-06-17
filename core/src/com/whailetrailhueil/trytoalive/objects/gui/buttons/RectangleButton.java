package com.whailetrailhueil.trytoalive.objects.gui.buttons;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.objects.AbstractObject;

/**
 * Created by User on 15.06.2016.
 */
public class RectangleButton extends Button {

    private Rectangle rectangle;

    public RectangleButton(AbstractObject parent, Vector2 center, float sizeX, float rotation, String nameOfTexture){
        super(parent, center, sizeX, rotation, nameOfTexture);
        rectangle = new Rectangle(getPosition().x,getPosition().y,getSize().x,getSize().y);
    }

    @Override
    public boolean isPressed(float x, float y) {
        return rectangle.contains(x,y);
    }
}
