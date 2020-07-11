package panic.game;

import Utilities.TextureLoader;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static panic.game.MainClass.staticMainClass;

public class GameClass extends ApplicationAdapter {
    SpriteBatch batch;


    @Override
    public void create() {
        TextureLoader.loadTexture();
        staticMainClass = new MainClass();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        staticMainClass.act();
        staticMainClass.draw();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
