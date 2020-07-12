package Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class SoundMaker {
    public Music battle;
    public Sound dash;
    public Sound doublejump;
    public Sound enemykilled;
    public Sound jump;
    public Sound lose;
    public Sound shoot;
    public Sound takedamage;

    public SoundMaker(){
        load();
    }

    public void load(){
        battle = Gdx.audio.newMusic(Gdx.files.internal("music/Battle1.wav"));
        dash = Gdx.audio.newSound(Gdx.files.internal("music/Dash.wav"));
        doublejump = Gdx.audio.newSound(Gdx.files.internal("music/DoubleJump.wav"));
        enemykilled = Gdx.audio.newSound(Gdx.files.internal("music/EnemyKilled.wav"));
        jump = Gdx.audio.newSound(Gdx.files.internal("music/Jump.wav"));
        lose = Gdx.audio.newSound(Gdx.files.internal("music/Lose.wav"));
        shoot = Gdx.audio.newSound(Gdx.files.internal("music/Shoot.wav"));
        takedamage = Gdx.audio.newSound(Gdx.files.internal("music/TakeDamage.wav"));

        battle.setVolume(1f);
        battle.setLooping(false);
        battle.play();
    }
}
