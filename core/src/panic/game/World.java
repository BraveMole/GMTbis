package panic.game;

import actors.Plateform;
import actors.Player;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class World extends Stage {
    public Player p = new Player(0, 0);
    public static World mainWorld;

    public World() {
        this.getViewport().getCamera().viewportHeight = this.getViewport().getScreenHeight();
        this.getViewport().getCamera().viewportWidth = this.getViewport().getScreenWidth();

    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void draw() {
        super.draw();
    }

}
