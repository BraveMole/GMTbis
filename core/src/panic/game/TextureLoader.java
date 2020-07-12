package panic.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class TextureLoader {
    public static AssetManager manager;
    public static TiledMap map;
    public static Texture Player;
    public static Texture Enemy;
    public static Texture Projectile;
    public static Texture[] KEYS = new Texture[14];
    public static Texture background;
    public static TextureRegion[] flyingEnemySpriteSheet;
    public static TextureRegion[] dyingEnemySpriteSheet;

    public static void loadTexture() {
        loadMap();
        loadTextureActors();
    }

    private static void loadTextureActors(){
        Player = new Texture("Triston.png");
        Enemy = new Texture("pacfrog2.png");
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
        Projectile = new Texture("placeHolderProjectile.png");
        background = new Texture("background.png");
        loadEnemiesTexture();
    }

    private static void loadEnemiesTexture(){
        TextureLoader.dyingEnemySpriteSheet = new TextureRegion[6];
        TextureRegion[][] tempFrames = TextureRegion.split(new Texture("Death_monster.png"), 96, 93);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                TextureLoader.dyingEnemySpriteSheet[j * 2 + i] = tempFrames[j][i];
            }
        }
        TextureLoader.flyingEnemySpriteSheet = new TextureRegion[4];
        tempFrames = TextureRegion.split(new Texture("Movement.png"), 100, 90);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                TextureLoader.flyingEnemySpriteSheet[j * 2 + i] = tempFrames[j][i];
            }
        }
    }

    private static void loadMap() {
        String filename ="Map1.tmx";
        manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load(filename, TiledMap.class);
        manager.finishLoading();
        manager.finishLoading();
        map = manager.get(filename, TiledMap.class);
    }
}
