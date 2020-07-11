package panic.game;


import Utilities.JumpableListener;
import Utilities.Settings;
import Utilities.TextureLoader;
import Utilities.BodyFactory;
import actors.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Set;


public class MainClass extends Stage {
    public static MainClass staticMainClass;
    private Player player;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private World world;
    Box2DDebugRenderer debugRenderer;

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    public MainClass() {
        camera = (OrthographicCamera)this.getViewport().getCamera();
        camera.zoom=1f;
        float ratio =this.getViewport().getScreenWidth()/ this.getViewport().getScreenHeight();
        this.getViewport().getCamera().viewportHeight = Settings.metersOnScreen*ratio;
        this.getViewport().getCamera().viewportWidth = Settings.metersOnScreen*ratio;
        renderer = new OrthogonalTiledMapRenderer(TextureLoader.map, Settings.metersToPixelRatio);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
        world = new World(new Vector2(0,-10),true);
        world.setContactListener(new JumpableListener());
        BodyFactory.initializeFactory(world);
        String layerName = "StillLayer";
        TextureLoader.buildBuildingsBodies(TextureLoader.map,world,layerName);
        player = new Player(10,4,1f,1f);
        this.addActor(player);
    }

    public Player getPlayer() {
        return player;
    }

    private void readInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.Q)) {//I'm french, I have azerty keyboard, not qwerty
            player.body.setLinearVelocity(-Settings.playerSpeed,player.body.getLinearVelocity().y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.body.setLinearVelocity(Settings.playerSpeed,player.body.getLinearVelocity().y);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)){

        }
    }


    @Override
    public void act() {
        readInput();
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
