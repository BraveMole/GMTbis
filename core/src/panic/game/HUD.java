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
    public Array<Key> keys;
    public int[] x = {100, 150, 50, 100, 250 ,300 ,350};
    public int[] y = {150, 100, 100, 100,125,125,125};

    public HUD(SpriteBatch batch){
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera());
        stage = new Stage(viewport,batch);
        keys = new Array<>();
        for (int z = 0; z < GameClass.character.totalcontrols.size(); z++) {
            Key k = new Key(x[z], y[z], TextureLoader.KEYS[2 * z], TextureLoader.KEYS[2 * z + 1], GameClass.character.totalcontrols.get(z));
            keys.add(k);
            stage.addActor(k);
        }

    }


}
