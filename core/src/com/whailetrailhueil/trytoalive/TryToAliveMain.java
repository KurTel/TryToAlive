package com.whailetrailhueil.trytoalive;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whailetrailhueil.trytoalive.Screens.CustomScreen;
import com.whailetrailhueil.trytoalive.Screens.ScreenManager;

public class TryToAliveMain extends Game {
	@Override
	public void create () {
		ScreenManager screenManager = ScreenManager.getInstance();
		screenManager.init(this);
		screenManager.show(CustomScreen.GAME_SCREEN);
	}
}
