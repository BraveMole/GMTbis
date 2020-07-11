package panic.game;

import actors.Plateform;
import actors.Player;
import actors.Key;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import ui.WorldInputProcessor;
import com.badlogic.gdx.scenes.scene2d.Group;
import panic.game.GameClass;
import javax.xml.soap.Text;

public class World extends Stage {
    public Group actors = new Group();
    public WorldInputProcessor inputProcessor;
    public int[] x = {200, 300, 100, 200};
    public int[] y = {200, 100, 100, 100};
    public boolean main;

    public World(boolean main) {
        this.main = main;
        if (main){
            actors.addActor(GameClass.character);
            inputProcessor = new WorldInputProcessor(this.getViewport(), this, GameClass.character);
        }
        else {
            for (int z = 0; z < GameClass.character.totalcontrols.size(); z++) {
                Key k = new Key(x[z], y[z], TextureLoader.KEYS[2 * z], TextureLoader.KEYS[2 * z + 1], GameClass.character.totalcontrols.get(z));
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
            this.inputProcessor.act();
        }
    }

    @Override
    public void draw() {
        super.draw();
    }

}
