package panic.game;

import Utilities.WorldInputProcessor;
import actors.Enemy;
import actors.Key;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import actors.Player;

public class World extends Stage {
    public Array<Key> keys;
    public Group actors = new Group();
    public Enemy e = new Enemy(700, 700);
    public WorldInputProcessor inputProcessor;
    public Player p;

    public int[] x = {100, 150, 50, 100, 250 ,300 ,350};
    public int[] y = {150, 100, 100, 100,125,125,125};
    public boolean main;

    public World(boolean main) {
        p = GameClass.character;
        this.main = main;
        if (main){
            actors.addActor(p);
            inputProcessor = new WorldInputProcessor(this.getViewport(), this, p);
            actors.addActor(e);
        }
        else {
            keys = new Array<>();
            for (int z = 0; z < p.totalcontrols.size(); z++) {
                Key k = new Key(x[z], y[z], TextureLoader.KEYS[2 * z], TextureLoader.KEYS[2 * z + 1], p.totalcontrols.get(z));
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
        if (main) {
            inputProcessor.act();
            float y = p.getY();
            if (y < 8200){
                y = 8200;
            }
            this.getViewport().getCamera().position.set(p.getX(), y, 0);
        }
    }



    @Override
    public void draw() {
        super.draw();
    }

}
