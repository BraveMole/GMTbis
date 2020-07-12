package actors;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import panic.game.TextureLoader;

public class Menu extends Stage {
    SpriteBatch batch;
    public Menu(){
        this.batch = (SpriteBatch)this.getBatch();
    }

    @Override
    public void draw() {
        batch.begin();
        batch.draw(TextureLoader.menu,0,0,1400,650);
        batch.draw(TextureLoader.play,500,280,300,150);
        batch.end();
        super.draw();
    }
}
