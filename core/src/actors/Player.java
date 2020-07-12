package actors;

import Utilities.AnimatedSprite;
import Utilities.Settings;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import panic.game.GameClass;
import panic.game.ObstacleBuilder;
import panic.game.TextureLoader;
import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;

import static java.lang.Math.PI;

public class Player extends SuperActor{
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
    private Rectangle collisionRectangle;
    private float width;
    private float height;

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
        width = text.getWidth() * Settings.playerSize;
        height = text.getHeight()*Settings.playerSize;
        this.sprite = new AnimatedSprite(text, x, y, -90, width, height);
        this.setX(x);
        this.setY(y);
        collisionRectangle = new Rectangle(x-width/2,y-height/2,width,height);
        this.setRotation(90);
    }

    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    public void fire(Vector3 fireDirection){
        float xdiff = fireDirection.x - this.getX();
        float ydiff = fireDirection.y - this.getY();
        float angle =(float)Math.atan(ydiff/xdiff);
        if (xdiff<0){
            angle+=PI;
        }
        Projectile projectile = new Projectile(this.getX(),this.getY(),angle);
        GameClass.mainWorld.addActor(projectile);
        GameClass.liveProjectiles.add(projectile);
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
            if(Intersector.overlaps(e.getCollisionPolygon().getBoundingRectangle(),this.getCollisionRectangle()) && (invis == 0)){
                invis = invisframes;
                controlsleft.remove(controlsleft.size() - 1);
            }

        }

        if ((controlsleft.contains("d")&& (walkies == 1)) || (controlsleft.contains("a") && (walkies == -1))) {
            xvel += walkies * Settings.playerSpeed;
        }

        if (usedash && dash){
            if (invis == 0) {
                System.out.println("Dash used");
                dash = false;
                invis = dashinvis;
                if (right) {
                    xvel += Settings.dashSpeed;
                }
                else{
                    xvel -= Settings.dashSpeed;
                }
            }
        }

        if (invis == 0 && !dash && usedash){
            System.out.println("Dash stopped");
            usedash = false;
        }
        collisionRectangle.set(this.getX()-width/2,this.getY()-height/2,width,height);

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

    public boolean onTheGround(){
        Rectangle placeHolder = new Rectangle();
        if (this.getY() < 0){
            return true;
        }
        for (Rectangle r : ObstacleBuilder.Bounds){
            if (Intersector.intersectRectangles(r,this.collisionRectangle,placeHolder)){
                return true;
            }
        }
        return false;
    }

    public boolean bumping(){
        Rectangle placeHolder = new Rectangle();
        for (Rectangle r : ObstacleBuilder.Bounds){
            Intersector.intersectRectangles(r,this.collisionRectangle,placeHolder);
            if (placeHolder.height>=10){
                return true;
            }
        }
        return false;
    }

}
