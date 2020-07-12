package panic.game;

import Utilities.WorldInputProcessor;
import actors.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

import java.util.EmptyStackException;

public class World extends Stage {
    public Group actors = new Group();
    public EnemyGenerator eg = new EnemyGenerator(7000, 1000);
    public WorldInputProcessor inputProcessor;
    public Player p;
    public Portal portal;

    public int[] x = {100, 150, 50, 100, 250, 300, 350};
    public int[] y = {150, 100, 100, 100, 125, 125, 125};

    public World() {
        p = GameClass.character;
        portal = new Portal(8000, 8500);
        actors.addActor(p);
        actors.addActor(portal);
        inputProcessor = new WorldInputProcessor(this.getViewport(), this, p);
        super.addActor(actors);
        this.getViewport().getCamera().viewportHeight = this.getViewport().getScreenHeight();
        this.getViewport().getCamera().viewportWidth = this.getViewport().getScreenWidth();
    }

    @Override
    public void act() {
        super.act();
        inputProcessor.act();
        eg.act();
        float y = p.getY();
        if (y < 8200) {
            y = 8200;
        }
        this.getViewport().getCamera().position.set(p.getX(), y, 0);
    }




    @Override
    public void draw() {
        super.draw();
    }

}
