package panic.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import panic.game.GameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.samples = 1;
		config.width = 1400;
		config.height = 650;
		config.resizable = true;
		new LwjglApplication(new GameClass(), config);
	}
}
