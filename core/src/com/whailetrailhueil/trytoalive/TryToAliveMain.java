package com.whailetrailhueil.trytoalive;

import com.badlogic.gdx.Game;
import com.whailetrailhueil.trytoalive.screens.CustomScreen;
import com.whailetrailhueil.trytoalive.screens.ScreenManager;

public class TryToAliveMain extends Game {

	@Override
	public void create () {
		ScreenManager screenManager = ScreenManager.getInstance();
		screenManager.init(this);
		screenManager.show(CustomScreen.GAME_SCREEN);
	}
}
