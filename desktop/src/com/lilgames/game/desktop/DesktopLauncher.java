package com.lilgames.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lilgames.game.MiniGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MiniGame.V_WIDTH;
		config.height = MiniGame.V_HEIGHT;
		config.title = MiniGame.TITLE;
		new LwjglApplication(new MiniGame(), config);
	}
}
