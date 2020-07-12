package actors;

import Utilities.AnimatedSprite;
import com.badlogic.gdx.graphics.Texture;
import panic.game.ObstacleBuilder;
import panic.game.TextureLoader;
import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;

public class Player extends SuperActor{
    static int speed = 20;
    static int height = 20;
    double xvel = 0f;
    double yvel = 0f;

    public static ArrayList<String> totalcontrols = new ArrayList<String>();
    public static ArrayList<String> controlsleft = new ArrayList<String>();
    public int walkies;
    public int uppies;

    public Player(float x, float y){
        totalcontrols.add("w");
        totalcontrols.add("d");
        totalcontrols.add("a");
        totalcontrols.add("s");
        totalcontrols.add("h");
        totalcontrols.add("j");
        totalcontrols.add("k");

        controlsleft = (ArrayList<String>) totalcontrols.clone();

        Texture text = TextureLoader.Player;
        this.sprite = new AnimatedSprite(text, x, y, -90, text.getWidth(), text.getHeight());
        this.setX(x);
        this.setY(y);
        this.setRotation(90);
    }

    @Override
    public void act(float delta) {
//        System.out.println(this.getX());
//        System.out.println(this.getY());
//        System.out.println(xvel);
        if ((controlsleft.contains("d") && (walkies == 1)) || (controlsleft.contains("a") && (walkies == -1))) {
            xvel += walkies * speed;
        }
        if (controlsleft.contains("w")){
            yvel += uppies * height;
        }

        xvel *= 0.5f;
        //yvel -= 1;

        if ((this.getY() < 0) && (yvel < 0)){
            this.setY(0);
            yvel = 0;
        }

        this.setX((float) (this.getX() + xvel));
        this.setY((float) (this.getY() + yvel));
        super.act(delta);
    }

    public static int collision(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
        // 1 = Object 1 collided on the right of Object 2
        // 2 = Object 1 collided on the left of Object 2
        // 3 = Object 1 collided on bottom of Object 2
        // 4 = Object 1 collided on top of Object 2
        if (x1 > x2 + width2)
            return 1;
        if (x1 + width1 < x2)
            return 2;
        if (y1 > y2 + height2)
            return 3;
        if (y1 + height1 < y2) {
            return 4;
        }
        return 0;
    }

    public boolean onTheGround(){
        for (Rectangle r : ObstacleBuilder.Bounds){
            if (collision(this.getX(), this.getY(), this.getWidth(), this.getHeight(), r.getX(), r.getY(), r.getWidth(), r.getHeight()) == 4){
                return true;
            }
        }
        return false;
    }
}
