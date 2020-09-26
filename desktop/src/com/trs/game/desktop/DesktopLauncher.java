package com.trs.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trs.game.Controller;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1600;
		config.height = 900;
		config.samples = 8;
		config.resizable = false;
		config.addIcon("icons/icon_128.png", Files.FileType.Internal);
		config.addIcon("icons/icon_32.png", Files.FileType.Internal);
		config.addIcon("icons/icon_16.png", Files.FileType.Internal);
		config.title = "StarveTheMonster";
		new LwjglApplication(new Controller(), config);
	}
}
