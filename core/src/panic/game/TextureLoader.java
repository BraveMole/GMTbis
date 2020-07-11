package panic.game;

import actors.Settings;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;

public class TextureLoader {
    public static AssetManager manager;
    public static TiledMap map;
    public static int tileWidth, tileHeight,
            mapWidthInTiles, mapHeightInTiles,
            mapWidthInPixels, mapHeightInPixels;

    public static Texture Player;
    public static int WIDTH = 20;
    public static int HEIGHT = 20;
    public static Texture[] KEYS = new Texture[13];

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
        KEYS[12] = new Texture("K_no.png");
    }

    private static void loadMap() {
        String filename ="testLevel2.tmx";
        manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load(filename, TiledMap.class);
        manager.finishLoading();
        manager.finishLoading();
        map = manager.get(filename, TiledMap.class);
        MapProperties properties = map.getProperties();
        tileWidth         = properties.get("tilewidth", Integer.class);
        tileHeight        = properties.get("tileheight", Integer.class);
        mapWidthInTiles   = properties.get("width", Integer.class);
        mapHeightInTiles  = properties.get("height", Integer.class);
        mapWidthInPixels  = mapWidthInTiles  * tileWidth;
        mapHeightInPixels = mapHeightInTiles * tileHeight;

    }
    public static void buildBuildingsBodies(TiledMap tiledMap, World world, String layer){
        MapObjects objects = tiledMap.getLayers().get(layer).getObjects();
        Rectangle rectangle = new Rectangle(0,0,0,0);
        for (MapObject object: objects) {
            if (object instanceof RectangleMapObject) {
                rectangle = ((RectangleMapObject) object).getRectangle();
                rectangle.setX((rectangle.getX()+rectangle.getWidth()/2f)*Settings.metersToPixelRatio);
                rectangle.setY((rectangle.getY()+rectangle.getHeight()/2f)*Settings.metersToPixelRatio);
                rectangle.setSize(rectangle.width*Settings.metersToPixelRatio,rectangle.height*Settings.metersToPixelRatio);
            }
            else if(object instanceof TiledMapTileMapObject){
                TiledMapTileMapObject tile = ((TiledMapTileMapObject) object);
                rectangle = new Rectangle(tile.getX(),tile.getY(),(float)(tile.getProperties().get("width")),(float)(tile.getProperties().get("height")));
                rectangle.setX((rectangle.getX()+rectangle.getWidth()/2)*Settings.metersToPixelRatio);
                rectangle.setY((rectangle.getY()+rectangle.getHeight()/2)*Settings.metersToPixelRatio);
                rectangle.setSize(rectangle.width*Settings.metersToPixelRatio,rectangle.height*Settings.metersToPixelRatio);
            }
        }
    }
}
