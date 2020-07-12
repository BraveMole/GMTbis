package panic.game;

import actors.Key;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class HUD  {
    public Stage stage;
    public Viewport viewport;
    public SpriteBatch batch;
    public Array<Key> keys;
    public int[] x = {50, 100, 0, 50, 175 ,225 ,275};
    public int[] y = {50, 0 , 0, 0,25,25,25};
    public int size = 200;

    public HUD(){
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera());
        stage = new Stage(viewport);
        this.batch=(SpriteBatch)stage.getBatch();
        keys = new Array<>();
        for (int z = 0; z < GameClass.character.totalcontrols.size(); z++) {
            Key k = new Key(x[z]-size/4, y[z]-size/4, TextureLoader.KEYS[2 * z], TextureLoader.KEYS[2 * z + 1], GameClass.character.totalcontrols.get(z));
            keys.add(k);
            stage.addActor(k);
        }
    }

    public void render(){
        for (Key key : keys) {
            key.act(0.1f);
        }
        batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
        batch.begin();
        for (Key key : keys) {
            batch.draw(key.getT(),key.getX(),key.getY(),size,size);
        }
        batch.end();
    }


}
