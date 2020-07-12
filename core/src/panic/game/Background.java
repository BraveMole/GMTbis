package panic.game;

import actors.Key;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Background {
    public Stage stage;
    public Viewport viewport;
    public SpriteBatch batch;
    public Background() {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport);
        this.batch = (SpriteBatch) stage.getBatch();
    }

    public void render(){
        batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
        batch.begin();
        batch.draw(TextureLoader.background,0,0,1600,650);
        batch.end();
    }
}
