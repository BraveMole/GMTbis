package panic.game;

import actors.Plateform;
import actors.Player;
import com.badlogic.gdx.scenes.scene2d.Stage;
import ui.WorldInputProcessor;


public class World extends Stage {
    public Player p = new Player(0, 0);
    public static World mainWorld;
    public WorldInputProcessor inputProcessor = new WorldInputProcessor(this.getViewport(), this, p);

    public World() {
        mainWorld = this;
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
