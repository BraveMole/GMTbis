package panic.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ObstacleBuilder {
    public static Array<Rectangle> Bounds;


    public static void buildBuildingsBodies(TiledMap tiledMap, String layer) {
        Bounds = new Array<>();
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

    public static Vector2 middleOfMap() {
        float minx = 100000;
        float miny = 1000000;
        float maxx = -1000000;
        float maxy = -1000000;
        for (Rectangle bound : Bounds) {
            if (bound.x < minx) {
                minx = bound.x;
            } else if (bound.x + bound.width > maxx) {
                maxx = bound.x+bound.width;
            }
            if (bound.y < miny) {
                miny = bound.y;
            } else if (bound.y + bound.height > maxy) {
                maxy = bound.y+bound.height;
            }
        }
        return new Vector2((minx+maxx)/2,maxy);
    }
}
