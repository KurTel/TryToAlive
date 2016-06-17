package com.whailetrailhueil.trytoalive.objects.gui;

import com.whailetrailhueil.trytoalive.objects.AbstractObject;
import com.whailetrailhueil.trytoalive.objects.gui.buttons.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15.06.2016.
 */
public class GameGUI extends AbstractObject {

    private List<Button> buttons;

    public GameGUI(){
        super();
        buttons = new ArrayList<Button>();
    }



}
