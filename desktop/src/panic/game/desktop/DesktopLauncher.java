package panic.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import panic.game.GameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.samples = 1;
		config.width = 1600;
		config.height = 650;
		config.resizable = false;
		new LwjglApplication(new GameClass(), config);
	}
}
