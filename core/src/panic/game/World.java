package panic.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Group;

public class World extends Stage {

    public Group actors = new Group();

    public int[] x = {100, 150, 50, 100, 250 ,300 ,350};
    public int[] y = {150, 100, 100, 100,125,125,125};

    public World(){
        actors.addActor(GameClass.character);
        super.addActor(actors);
    }


    @Override
    public void act() {
        super.act();
    }




    @Override
    public void draw() {
        this.getViewport().getCamera().position.set(GameClass.character.getX(), GameClass.character.getY(), 0);
        this.getViewport().getCamera().update();
        super.draw();
    }

}
