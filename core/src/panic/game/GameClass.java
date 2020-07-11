package panic.game;

import Utilities.Settings;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import actors.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameClass extends ApplicationAdapter {
    public static World mainWorld;
    public static HUD hud;
    public static Player character;
    public static ShapeRenderer debugrender;
    public static boolean debug = false;
    private OrthogonalTiledMapRenderer renderer;

    @Override
    public void create() {
        TextureLoader.loadTexture();
        ObstacleBuilder.buildBuildingsBodies(TextureLoader.map, Settings.layerName);
        character = new Player(ObstacleBuilder.middleOfMap().x,ObstacleBuilder.middleOfMap().y+100);
        mainWorld = new World();
        hud = new HUD((SpriteBatch)mainWorld.getBatch());
        renderer = new OrthogonalTiledMapRenderer(TextureLoader.map);
        debugrender = new ShapeRenderer();


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

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
            debugrender.end();
        }
    }

    @Override
    public void dispose() {

    }
}
