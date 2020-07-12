package actors;

import Utilities.Settings;
import com.badlogic.gdx.Gdx;
import com.sun.javafx.sg.prism.NGAmbientLight;
import panic.game.GameClass;

import java.util.Set;

public class EnemyGenerator {
    int maxenemies;
    float x;
    float y;
    float xdiff;
    float ydiff;
    float timeSinceLastAugmentationOfEnemies;
    float timeSinceLastApparition;

    public EnemyGenerator(float x, float y, float xdiff, float ydiff){
        maxenemies = 10;
        this.x = x;
        this.y = y;
        this.xdiff = xdiff;
        this.ydiff = ydiff;
        timeSinceLastAugmentationOfEnemies = 0;
        timeSinceLastApparition=0;
    }

    public void act(){
        timeSinceLastAugmentationOfEnemies += Gdx.graphics.getDeltaTime();
        timeSinceLastApparition+=Gdx.graphics.getDeltaTime();
        if (timeSinceLastApparition> Settings.timeBetweenTwoApparition){
            timeSinceLastApparition =0;
            if (GameClass.enemies.size < maxenemies) {
                float xen =(float)( x + Math.random() * xdiff);
                float yen = (float) (y + Math.random() * ydiff);
                if (Math.abs(xen-GameClass.character.getX())>500 || Math.abs(yen-GameClass.character.getY())>500 ) {
                    Enemy e = new Enemy(xen, yen);
                    GameClass.enemies.add(e);
                    GameClass.mainWorld.actors.addActor(e);
                }
                else{
                    timeSinceLastApparition= Settings.timeBetweenTwoApparition;
                }
            }
        }
        if (timeSinceLastAugmentationOfEnemies>Settings.timeBetweenTwoAugmentation){
            timeSinceLastAugmentationOfEnemies=0;
            maxenemies+=1;
        }

    }
}
