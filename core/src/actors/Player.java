package actors;

import Utilities.AnimatedSprite;
import Utilities.Animation;
import Utilities.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import panic.game.GameClass;
import panic.game.ObstacleBuilder;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;

import static java.lang.Math.PI;

public class Player extends SuperActor{
    final static int invisframes = 120;
    final static int dashinvis = 5;

    boolean right = false;
    boolean doublejump = false;
    boolean dash = false;
    boolean groundpound = false;
    int invis = 0;
    double xvel = 0f;
    double yvel = 0f;

    float spawnx = 0;
    float spawny = 0;

    public static ArrayList<String> totalcontrols = new ArrayList<String>();
    public static ArrayList<String> controlsleft = new ArrayList<String>();
    public int walkies;
    public int uppies;
    public boolean usedoublejump = false;
    public boolean usedash = false;
    public boolean usegroundpound = false;
    private Rectangle collisionRectangle;
    private float width;
    private float height;
    private boolean moving;
    private boolean goesToTheRight;
    private float timeToStop;

    public void setGoesToTheRight(boolean goesToTheRight) {
        this.goesToTheRight = goesToTheRight;
    }

    public Player(float x, float y){
        spawnx = x;
        spawny = y;

        totalcontrols.add("w");
        totalcontrols.add("d");
        totalcontrols.add("a");
        totalcontrols.add("s");
        totalcontrols.add("h");
        totalcontrols.add("j");
        totalcontrols.add("k");

        controlsleft = (ArrayList<String>) totalcontrols.clone();
        this.setSprite(Animation.CHARACTER_STOPPING);
        width = Animation.CHARACTER_STOPPING.getWidth();
        height = Animation.CHARACTER_STOPPING.getHeight();
        this.setX(x);
        this.setY(y);
        collisionRectangle = new Rectangle(x-width/2,y-height/2,width,height);
        this.setRotation(90);
    }

    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    public void fire(Vector3 fireDirection){
        GameClass.sm.shoot.play();
        float xdiff = fireDirection.x - this.getX();
        float ydiff = fireDirection.y - this.getY();
        float angle = (float) Math.atan(ydiff/xdiff);
        if (xdiff < 0){
            angle += PI;
        }
        Projectile projectile = new Projectile(this.getX(), this.getY(), angle);
        GameClass.mainWorld.addActor(projectile);
        GameClass.liveProjectiles.add(projectile);
        if (GameClass.liveProjectiles.size <Settings.maxProjectiles) {
            float xdiff = fireDirection.x - this.getX();
            float ydiff = fireDirection.y - this.getY();
            float angle = (float) Math.atan(ydiff / xdiff);
            if (xdiff < 0) {
                angle += PI;
            }
            Projectile projectile = new Projectile(this.getX(), this.getY(), angle);
            GameClass.mainWorld.addActor(projectile);
            GameClass.liveProjectiles.add(projectile);
        }
    }

    public void respawn(){
        GameClass.sm.lose.play();
        controlsleft = (ArrayList<String>) totalcontrols.clone();
        this.setX(spawnx);
        this.setY(spawny);
        while (GameClass.liveProjectiles.size > 0) {
            Projectile p = GameClass.liveProjectiles.removeIndex(0);
            GameClass.mainWorld.actors.removeActor(p);
        }
        while (GameClass.enemies.size > 0){
            GameClass.enemies.get(0).die(false);
        }
    }

    @Override
    public void act(float delta) {
        if (this.getY() < 7800){
            respawn();
        }
        if (controlsleft.size() == 0){
            respawn();
        }

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
            if (Intersector.overlaps(e.getCollisionPolygon().getBoundingRectangle(),this.getCollisionRectangle()) && (invis == 0)) {
                if (groundpound){
                    e.die(true);
                }
                else {
                    GameClass.sm.takedamage.play();
                    invis = invisframes;
                    controlsleft.remove(controlsleft.size() - 1);
                }
            }

        }

        if ((controlsleft.contains("d") && (walkies == 1)) || (controlsleft.contains("a") && (walkies == -1))) {
            xvel += walkies * Settings.playerSpeed;
        }

        if (controlsleft.contains("j") && usedash && dash){
            if (invis == 0) {
                GameClass.sm.dash.play();
//                System.out.println("Dash used");
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

        if (controlsleft.contains("h") && usedoublejump && doublejump) {
            if (invis == 0) {
                GameClass.sm.doublejump.play();
                doublejump = false;
                invis = dashinvis;
                yvel += Settings.jumpheight;
            }
        }

        if (invis == 0 && !dash && usedash){
//            System.out.println("Dash stopped");
            usedash = false;
        }
        if (invis == 0 && !doublejump && usedoublejump){
            usedoublejump = false;
        }
        collisionRectangle.set(this.getX()-width/2,this.getY()-height/2,width,height);

        if (onTheGround()) {
            groundpound = false;
            usegroundpound = false;
            if (controlsleft.contains("w")) {
                if (uppies != 0){
                    GameClass.sm.jump.play();
                }
                yvel += uppies * Settings.jumpheight;
            }
            if (controlsleft.contains("j") && !usedash && !dash){
//                System.out.println("Dash restored");
                dash = true;
            }
            if (controlsleft.contains("h") && !usedoublejump && !doublejump){
                doublejump = true;
            }
        }
        else{
            if (controlsleft.contains("s") && usegroundpound && !groundpound){
                groundpound = true;
                usegroundpound = false;
            }
        }

        xvel *= Settings.friction;

        if (groundpound) {
            yvel -= Settings.gravity * Settings.groundpound;
        }
        else{
            yvel -= Settings.gravity;
        }

        yvel *= Settings.airresistance;


        if ((onTheGround() || (this.getY() < 0)) && (yvel <= 0)){
            yvel = 0;
        }

        float originalX = this.getX();
        float originalY = this.getY();

        this.setX((float) (this.getX() + xvel));
        this.setY((float) (this.getY() + yvel));




        if (bumpingDown()){
            this.setY(originalY+5);
            if (yvel<=0){
                yvel =-yvel*0.3f;
            }
            else{
                yvel+=1;
            };
        }
        if(bumpingUp()){
            this.setY(originalY-5);
            if (yvel>=0){
                yvel =-yvel*0.3f;
            }
            else{
                yvel-=1;
            }
        }

        if(bumpingLeft()){
            this.setX(originalX+5);
            if (xvel<=0){
                xvel =-xvel*0.3f;
            }
            else{
                xvel+=1;
            }
        }
        else if(bumpingRight()){
            this.setX(originalX-5);
            if (xvel>=0){
                xvel =-xvel*0.3f;
            }
            else{
                xvel-=1;
            }
        }

        if (goesToTheRight){
            collisionRectangle.set(this.getX()-width/2+0.3f*width,this.getY()-height/2,width*0.7f,height);
        }
        else{
            collisionRectangle.set(this.getX()-width/2,this.getY()-height/2,width*0.7f,height);
        }
        if (xvel>5){
            goesToTheRight = true;
        }
        else if (xvel<-5){
            goesToTheRight = false;
        }



        super.act(delta);
        if (moving & Math.abs(xvel)<5){
            moving = false;
            timeToStop = 0;
            if (goesToTheRight){
                this.setSprite(Animation.CHARACTER_STOPPING_BIS);
            }
            else{
                this.setSprite(Animation.CHARACTER_STOPPING);
            }
        }
        if (!moving & Math.abs(xvel)<5){
            timeToStop+= Gdx.graphics.getDeltaTime();
            if (timeToStop>Animation.CHARACTER_STOPPING.getAnimationDuration()){
                if (goesToTheRight){
                    this.setSprite(Animation.CHARACTER_STOPPED_BIS);
                }
                else{
                    this.setSprite(Animation.CHARACTER_STOPPED);
                }
            }
        }
        else if (!moving & Math.abs(xvel)>5){
            moving = true;
            if (goesToTheRight){
                this.setSprite(Animation.CHARACTER_RUNNING_BIS);
            }
            else{
                this.setSprite(Animation.CHARACTER_RUNNING);
            }
        }
    }

    public boolean onTheGround(){
        Rectangle placeHolder = new Rectangle();
        for (Rectangle r : ObstacleBuilder.Bounds){
            if (Intersector.intersectRectangles(r,this.collisionRectangle,placeHolder)){
                if (placeHolder.getY()<this.getY()){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean bumpingDown(){
        Rectangle placeHolder = new Rectangle();
        for (Rectangle r : ObstacleBuilder.Bounds){
            if (Intersector.intersectRectangles(r,this.collisionRectangle,placeHolder)){
                if (placeHolder.getY()<this.getY() & placeHolder.getHeight()>5){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean bumpingUp(){
        Rectangle placeHolder = new Rectangle();
        for (Rectangle r : ObstacleBuilder.Bounds){
            if (Intersector.intersectRectangles(r,this.collisionRectangle,placeHolder)){
                if (placeHolder.getY()>this.getY() & placeHolder.getHeight()>5){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean bumpingLeft(){
        Rectangle placeHolder = new Rectangle();
        for (Rectangle r : ObstacleBuilder.Bounds){
            if (Intersector.intersectRectangles(r,this.collisionRectangle,placeHolder)){
                if (placeHolder.getX()<this.getX() & placeHolder.getWidth()>5 & placeHolder.getHeight()>10){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean bumpingRight(){
        Rectangle placeHolder = new Rectangle();
        for (Rectangle r : ObstacleBuilder.Bounds){
            if (Intersector.intersectRectangles(r,this.collisionRectangle,placeHolder)){
                if (placeHolder.getX()>this.getX() & placeHolder.getWidth()>5  & placeHolder.getHeight()>10){
                    return true;
                }
            }
        }
        return false;
    }

}
