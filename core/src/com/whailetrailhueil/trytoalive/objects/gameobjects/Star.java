package com.whailetrailhueil.trytoalive.objects.gameObjects;

import com.badlogic.gdx.math.Vector2;
import com.whailetrailhueil.trytoalive.helpers.NAMES_OF_SPRITES;
import com.whailetrailhueil.trytoalive.objects.AbstractObject;

/**
 * Created by User on 14.06.2016.
 */
public class Star extends AbstractObject {
    public Star(AbstractObject parent, Vector2 position, float sizeX) {
        super(parent,position,sizeX,0, NAMES_OF_SPRITES.CIRCLE);
    }
}
