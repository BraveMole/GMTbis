package panic.game;

import actors.Key;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class World extends Stage {
    public Array<Key> keys;
    public Group actors = new Group();
    public int[] x = {100, 150, 50, 100};
    public int[] y = {150, 100, 100, 100};
    public boolean main;

    public World(boolean main) {
        this.main = main;
        if (main){
            actors.addActor(GameClass.character);
        }
        else {
            keys = new Array<>();
            for (int z = 0; z < GameClass.character.totalcontrols.size(); z++) {
                Key k = new Key(x[z], y[z], TextureLoader.KEYS[2 * z], TextureLoader.KEYS[2 * z + 1], GameClass.character.totalcontrols.get(z));
                keys.add(k);
                actors.addActor(k);
            }
        }
        super.addActor(actors);
        this.getViewport().getCamera().viewportHeight = this.getViewport().getScreenHeight();
        this.getViewport().getCamera().viewportWidth = this.getViewport().getScreenWidth();
    }

    @Override
    public void act() {
        super.act();
    }

    public void watchInputs(){
        if (Gdx.input.isKeyPressed(Input.Keys.D)){

        }
    }



    @Override
    public void draw() {
        super.draw();
    }

}
