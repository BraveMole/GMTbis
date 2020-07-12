package actors;

import Utilities.AnimatedSprite;
import com.badlogic.gdx.graphics.Texture;
import panic.game.GameClass;
import panic.game.ObstacleBuilder;
import panic.game.TextureLoader;
import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;

public class Player extends SuperActor{
    final static int speed = 20;
    final static int dashspeed = 500;
    final static int height = 50;
    final static int invisframes = 240;
    final static int dashinvis = 10;

    boolean right = false;
    boolean dash = false;
    int invis = 0;
    double xvel = 0f;
    double yvel = 0f;

    public static ArrayList<String> totalcontrols = new ArrayList<String>();
    public static ArrayList<String> controlsleft = new ArrayList<String>();
    public int walkies;
    public int uppies;
    public boolean usedash = false;

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
        if (walkies == -1 && right){
            right = false;
        }
        if (walkies == 1 && !right){
            right = true;
        }

        if (invis != 0){
            invis--;
        }
        for (Enemy e : GameClass.enemies){
            int a = collide(this.getX(), this.getY(), this.getWidth(), this.getHeight(), e.getX(), e.getY(), e.getWidth(), e.getHeight());
            if (a == 0){
                invis = invisframes;
            }
        }

        if ((controlsleft.contains("d")&& (walkies == 1)) || (controlsleft.contains("a") && (walkies == -1))) {
            xvel += walkies * speed;
        }

        if (usedash && dash){
            if (invis == 0) {
                System.out.println("Dash used");
                dash = false;
                invis = dashinvis;
                if (right) {
                    xvel += dashspeed;
                }
                else{
                    xvel -= dashspeed;
                }
            }
        }

        if (invis == 0 && !dash && usedash){
            System.out.println("Dash stopped");
            usedash = false;
        }

        if (onTheGround()) {
            if (controlsleft.contains("w")) {
                yvel += uppies * height;
            }
            if (controlsleft.contains("h") && !usedash && !dash){
                System.out.println("Dash restored");
                dash = true;
            }
        }

        xvel *= 0.5f;
        yvel -= 1;
        yvel *= 0.9f;


        if ((onTheGround() || (this.getY() < 0)) && (yvel <= 0)){
            yvel = 0;
        }

        float originalX = this.getX();
        float originalY = this.getY();

        this.setX((float) (this.getX() + xvel));
        this.setY((float) (this.getY() + yvel));


        if (bumping()){
            this.setX(originalX);
            this.setY(originalY);
        }

        super.act(delta);
    }

    public static int collide(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
/*
        if (x1 < x2 + width2 && x1 + width1 > x2 && y1 < y2 + height2 && y1 + height1 > y2) {
            return true;
        }
        return false;

 */
        if (x1 > x2 + width2){
            return 1;
        }
        if (x1 + width1 < x2){
            return 2;
        }
        if (y1 > y2 + height2){
            return 3;
        }
        if (y1 + height1 < y2){
            return 4;
        }
        return 0;
    }

    public boolean onTheGround(){
        if (this.getY() < 0){
            return true;
        }

        for (Rectangle r : ObstacleBuilder.Bounds){
            int a = collide(this.getX(), this.getY(), this.getWidth(), this.getHeight(), r.getX(), r.getY(), r.getWidth(), r.getHeight());
            if ((a == 0) && (this.getY() + r.height > r.getY())){
                return true;
            }
        }
        return false;
    }

    public boolean bumping(){
        for (Rectangle r : ObstacleBuilder.Bounds){
            int a = collide(this.getX(), this.getY(), this.getWidth(), this.getHeight(), r.getX(), r.getY(), r.getWidth(), r.getHeight());
            if ((a == 0) && (this.getY() < r.getY() + r.getHeight() - 10)){
                return true;
            }
        }
        return false;
    }

}
