package panic.game;

import actors.Plateform;
import actors.Player;
import actors.Key;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import ui.WorldInputProcessor;
import com.badlogic.gdx.scenes.scene2d.Group;

import javax.xml.soap.Text;

public class World extends Stage {
    public Player p = new Player(0, 0);
    public Group keys = new Group();
    public static World mainWorld;
    public WorldInputProcessor inputProcessor = new WorldInputProcessor(this.getViewport(), this, p);
    public int[] x = {200, 100, 200, 300};
    public int[] y = {200, 100, 100, 100};

    public World() {
        mainWorld = this;
        for (int z = 0; z < p.totalcontrols.size(); z++){
            Key k = new Key(x[z], y[z], TextureLoader.KEYS[2 * z], TextureLoader.KEYS[2 * z + 1], p.totalcontrols.get(z));
            keys.addActor(k);
        }

        super.addActor(keys);
        super.addActor(p);
        this.getViewport().getCamera().viewportHeight = this.getViewport().getScreenHeight();
        this.getViewport().getCamera().viewportWidth = this.getViewport().getScreenWidth();
    }

    @Override
    public void act() {
        super.act();
        this.inputProcessor.act();
    }

    @Override
    public void draw() {
        super.draw();
    }

}
