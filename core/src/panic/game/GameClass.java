package panic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static panic.game.World.mainWorld;

public class GameClass extends ApplicationAdapter {
    SpriteBatch batch;


    @Override
    public void create() {
        TextureLoader.loadTexture();
        mainWorld = new World();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        mainWorld.act();
        mainWorld.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
