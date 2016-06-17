package com.whailetrailhueil.trytoalive.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.whailetrailhueil.trytoalive.TryToAliveMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Title";
		config.height = 480;
		config.width = 800;
		new LwjglApplication(new TryToAliveMain(), config);
	}
}
