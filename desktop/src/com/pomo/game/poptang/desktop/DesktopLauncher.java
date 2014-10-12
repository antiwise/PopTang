package com.pomo.game.poptang.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Rectangle;
import com.pomo.game.poptang.PopTangGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
//		Rectangle r1 = new Rectangle(0, 0, 48, 48);
//		Rectangle r2 = new Rectangle(24, 24, 48, 48);
//		
//		System.out.println(r1.overlaps(r2));
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1024;
		config.height=576;
		
		new LwjglApplication(new PopTangGame(), config);
	}
}
