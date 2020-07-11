package panic.game;


import Utilities.TextureLoader;
import Utilities.BodyFactory;
import actors.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MainClass extends Stage {
    public static MainClass staticMainClass;
    public static Player player;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private OrthographicCamera camera2;
    private World world;
    Box2DDebugRenderer debugRenderer;

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    public World getWorld() {
        return world;
    }

    public MainClass() {
        camera = (OrthographicCamera)this.getViewport().getCamera();
        this.getViewport().getCamera().viewportHeight = this.getViewport().getScreenHeight();
        this.getViewport().getCamera().viewportWidth = this.getViewport().getScreenWidth();
        camera.position.set(this.getViewport().getScreenWidth()*(3/4),this.getViewport().getScreenHeight()*(3/4),0);
        renderer = new OrthogonalTiledMapRenderer(TextureLoader.map);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        world = new World(new Vector2(0,-1000f),true);
        BodyFactory.initializeFactory(world);
        String layerName = "StillLayer";
        TextureLoader.buildBuildingsBodies(TextureLoader.map,world,layerName);
        player = new Player(0,100,1f);
        this.addActor(player);
    }

    @Override
    public void act() {
        world.step(Gdx.graphics.getDeltaTime() , 6, 3);
        super.act();
    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.camera.position.set(player.getX(),player.getY(),0);
        camera.update();
        renderer.setView(camera);
        debugRenderer.render(world, camera.combined);
        renderer.render();

        super.draw();
    }



}
