package panic.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class TextureLoader {
    public static AssetManager manager;
    public static TiledMap map;
    public static Texture Player;
    public static Texture[] KEYS = new Texture[14];

    public static void loadTexture() {
        loadMap();
        loadTextureActors();
    }

    private static void loadTextureActors(){
        Player = new Texture("Triston.png");
        KEYS[0] = new Texture("W.png");
        KEYS[1] = new Texture("W_no.png");
        KEYS[2] = new Texture("D.png");
        KEYS[3] = new Texture("D_no.png");
        KEYS[4] = new Texture("A.png");
        KEYS[5] = new Texture("A_no.png");
        KEYS[6] = new Texture("S.png");
        KEYS[7] = new Texture("S_no.png");
        KEYS[8] = new Texture("H.png");
        KEYS[9] = new Texture("H_no.png");
        KEYS[10] = new Texture("J.png");
        KEYS[11] = new Texture("J_no.png");
        KEYS[12] = new Texture("K.png");
        KEYS[13] = new Texture("K_no.png");
    }

    private static void loadMap() {
        String filename ="MAPS/Map1.tmx";
        manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load(filename, TiledMap.class);
        manager.finishLoading();
        manager.finishLoading();
        map = manager.get(filename, TiledMap.class);
    }
}
