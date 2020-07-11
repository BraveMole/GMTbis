package actors;

import com.badlogic.gdx.graphics.Texture;
import panic.game.TextureLoader;
import java.util.ArrayList;

public class Player extends SuperActor{
    static int speed = 20;
    static int height = 20;
    double xvel = 0f;
    double yvel = 0f;

    static ArrayList<String> controlsleft = new ArrayList<String>();
    public int walkies;
    public int uppies;

    public Player(float x, float y){
        controlsleft.add("w");
        controlsleft.add("a");
        controlsleft.add("s");
        controlsleft.add("d");

        Texture text = TextureLoader.Player;
        this.sprite = new AnimatedSprite(text, x, y, -90, text.getWidth(), text.getHeight());
        this.setX(x);
        this.setY(y);
        this.setRotation(90);
    }

    @Override
    public void act(float delta) {
        System.out.println(this.getX());
        System.out.println(this.getY());
        System.out.println(xvel);
        if ((controlsleft.contains("d") && (walkies == 1)) || (controlsleft.contains("a") && (walkies == -1))) {
            xvel += walkies * speed;
        }
        if (onTheGround() && controlsleft.contains("w")){
            yvel += uppies * height;
        }

        xvel *= 0.5f;
        yvel -= 1;

        if ((this.getY() < 0) && (yvel < 0)){
            this.setY(0);
            yvel = 0;
        }

        this.setX((float) (this.getX() + xvel));
        this.setY((float) (this.getY() + yvel));
        super.act(delta);
    }

    public boolean onTheGround(){
        // TODO Return if player is on the ground
        return this.getY() < 0;
    }
}
