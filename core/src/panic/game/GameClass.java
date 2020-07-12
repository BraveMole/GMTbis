package panic.game;

import Utilities.Settings;
import Utilities.SoundMaker;
import actors.Enemy;
import actors.Projectile;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import actors.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public class GameClass extends ApplicationAdapter {
    public static World mainWorld;
    public static HUD hud;
    public static Background background;
    public static Player character;
    public static Array<Projectile> liveProjectiles;
    public static ShapeRenderer debugrender;
    public static Array<Enemy> enemies;
    public static boolean debug = false;
    public static SoundMaker sm;
    private OrthogonalTiledMapRenderer renderer;

    @Override
    public void create() {
        TextureLoader.loadTexture();
        ObstacleBuilder.buildBuildingsBodies(TextureLoader.map, Settings.layerName1);
        ObstacleBuilder.buildBuildingsBodies(TextureLoader.map, Settings.layerName2);
        character = new Player(ObstacleBuilder.middleOfMap().x,ObstacleBuilder.middleOfMap().y+100);
        background = new Background();
        mainWorld = new World();
        hud = new HUD();
        renderer = new OrthogonalTiledMapRenderer(TextureLoader.map);
        debugrender = new ShapeRenderer();
        enemies = new Array<>();
        liveProjectiles = new Array<>();
        sm = new SoundMaker();
    }

    public void addLiveProjectile(Projectile projectile){
        liveProjectiles.add(projectile);
    }

    public void removeLiveProjectile(Projectile projectile){
        liveProjectiles.removeValue(projectile,false);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        background.render();
        renderer.setView((OrthographicCamera)mainWorld.getViewport().getCamera());
        renderer.render();


        mainWorld.act();
        mainWorld.draw();

        if (debug) {
            debugrender.setProjectionMatrix(mainWorld.getViewport().getCamera().combined);
            debugrender.begin(ShapeRenderer.ShapeType.Line);
            debugrender.setColor(1, 1, 0, 1);
            for (int i = 0; i < ObstacleBuilder.Bounds.size; i++) {
                debugrender.rect(ObstacleBuilder.Bounds.get(i).x, ObstacleBuilder.Bounds.get(i).y, ObstacleBuilder.Bounds.get(i).width, ObstacleBuilder.Bounds.get(i).height);
            }
            debugrender.rect(character.getCollisionRectangle().x,character.getCollisionRectangle().y,character.getCollisionRectangle().width,character.getCollisionRectangle().height);
            for (Enemy enemy : enemies) {
                debugrender.polygon(enemy.getCollisionPolygon().getTransformedVertices());
            }
            for (Projectile liveProjectile : liveProjectiles) {
                debugrender.polygon(liveProjectile.getCollisionPolygon().getTransformedVertices());
            }
            debugrender.end();
        }
        hud.render();
    }

    @Override
    public void dispose() {

    }
}
