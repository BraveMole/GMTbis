package panic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameClassTest extends ApplicationAdapter {

    private TiledMap map;
    private AssetManager manager;

    @Override
    public void create () {
        manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load("maps/level.tmx", TiledMap.class);
        manager.finishLoading();

        map = manager.get("maps/level.tmx", TiledMap.class);
    }

    @Override
    public void dispose () {
        manager.dispose();
    }
}
