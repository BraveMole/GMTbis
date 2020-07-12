package panic.game;

import Utilities.BodyFactory;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class ObstacleBuilder {
    public static Array<Rectangle> Bounds = new Array<>();

    public static void buildBuildingsBodies(TiledMap tiledMap, String layer) {
        MapObjects objects = tiledMap.getLayers().get(layer).getObjects();
        Rectangle rectangle;
        for (MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
                rectangle = ((RectangleMapObject) object).getRectangle();
                Bounds.add(rectangle);
            } else if (object instanceof TiledMapTileMapObject) {
                TiledMapTileMapObject tile = ((TiledMapTileMapObject) object);
                rectangle = new Rectangle(tile.getX(), tile.getY(), (float) (tile.getProperties().get("width")), (float) (tile.getProperties().get("height")));
                Bounds.add(rectangle);
            }
        }
    }
}