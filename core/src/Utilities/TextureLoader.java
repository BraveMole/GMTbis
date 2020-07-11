package Utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import static Utilities.BodyFactory.createBody;

public class TextureLoader {
    public static Texture Player;
    public static AssetManager manager;
    public static TiledMap map;
    public static int tileWidth, tileHeight,
            mapWidthInTiles, mapHeightInTiles,
            mapWidthInPixels, mapHeightInPixels;

    public static void loadTexture() {
        loadMap();
        loadTextureActors();
    }

    private static void loadTextureActors(){
        Player = new Texture("Triston.png");
    }


    private static void loadMap() {
        String filename ="testLevel2.tmx";
        manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load(filename, TiledMap.class);
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
            createBody(rectangle,"Static",true);
        }
    }
}
