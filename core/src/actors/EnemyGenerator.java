package actors;

import Utilities.Settings;
import com.badlogic.gdx.Gdx;
import panic.game.GameClass;

import java.util.Set;

public class EnemyGenerator {
    float xdiff;
    float ydiff;
    int maxenemies;
    float timeSinceLastAugmentationOfEnemies;
    float timeSinceLastApparition;

    public EnemyGenerator( float xdiff, float ydiff){
        this.xdiff = xdiff;
        this.ydiff = ydiff;
        timeSinceLastAugmentationOfEnemies = 0;
        timeSinceLastApparition=0;
        maxenemies = Settings.maxEnemies;
    }

    public void act(){
        timeSinceLastAugmentationOfEnemies += Gdx.graphics.getDeltaTime();
        timeSinceLastApparition+=Gdx.graphics.getDeltaTime();
        if (timeSinceLastApparition> Settings.timeBetweenTwoApparition){
            timeSinceLastApparition =0;
            if (GameClass.enemies.size < maxenemies) {
                float xen =(float)( GameClass.character.getX() + Math.random() * xdiff-xdiff/2);
                float yen = (float) (GameClass.character.getY() + Math.random() * ydiff);
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
