package actors;

import panic.game.GameClass;

public class EnemyGenerator {
    float maxenemies = 10;
    float x;
    float y;
    float xdiff;
    float ydiff;
    float framesper;
    float framescur;

    public EnemyGenerator(float x, float y, float xdiff, float ydiff, float frames){
        this.x = x;
        this.y = y;
        this.xdiff = xdiff;
        this.ydiff = ydiff;
        framesper = frames;
    }

    public void act(){
        framescur++;
        if (framescur > framesper){
            if (GameClass.enemies.size < maxenemies) {
                framescur = 0;
                Enemy e = new Enemy((float) (x + Math.random() * xdiff), (float) (y + Math.random() * ydiff));
                GameClass.mainWorld.actors.addActor(e);
            }
            else{
                Enemy e = GameClass.enemies.removeIndex(0);
                GameClass.mainWorld.actors.removeActor(e);
            }
        }
    }
}
