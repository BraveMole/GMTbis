package Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import panic.game.GameClass;
import panic.game.World;
import actors.Player;

public class WorldInputProcessor implements InputProcessor {
    public Player player;
    private boolean rightButtonHold = false;
    private Vector3 originMoveCam;
    private Viewport viewport;
    private World worldStage;
    private float panduration = 1f;
    private float timeToCameraPositionTarget = 0;
    private float xcameraori;
    private float ycameraori;
    private float xcameradest;
    private float ycameradest;
    private float zoomduration = 0.4f;
    private float cameraZoomOrigin = 1f;
    private float cameraZoomTarget = 1f;
    private float timeToCameraZoomTarget = 0;


    public WorldInputProcessor(Viewport viewport, World stage, Player p) {
        this.worldStage = stage;
        Gdx.input.setInputProcessor(this);
        this.viewport = viewport;
        this.originMoveCam = new Vector3();
        this.player = p;
    }


    public void act() {
        this.renderPan();
    }

    @Override
    public boolean keyDown(int keycode) {
        if ((keycode == Input.Keys.D) || (keycode == Input.Keys.RIGHT)){
            player.walkies = 1;
            player.setGoesToTheRight(true);
            return true;
        }
        if ((keycode == Input.Keys.A) || (keycode == Input.Keys.LEFT)|| (keycode==Input.Keys.Q)){
            player.walkies = -1;
            player.setGoesToTheRight(false);
            return true;
        }
        if ((keycode == Input.Keys.W) || (keycode == Input.Keys.UP) || (keycode==Input.Keys.Z)){
            player.uppies = 1;
            return true;
        }
        if (keycode == Input.Keys.J){
            player.usedash = true;
        }
        if (keycode == Input.Keys.H){
            player.usedoublejump = true;
        }
        if (keycode == Input.Keys.S|| (keycode == Input.Keys.DOWN)){
            player.usegroundpound = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if ((keycode == Input.Keys.D) || (keycode == Input.Keys.RIGHT)){
            player.walkies = 0;
            return true;
        }
        if ((keycode == Input.Keys.A) || (keycode == Input.Keys.LEFT)|| (keycode==Input.Keys.Q) ){
            player.walkies = 0;
            return true;
        }
        if ((keycode == Input.Keys.W) || (keycode == Input.Keys.UP) || (keycode==Input.Keys.Z)){
            player.uppies = 0;
            return true;
        }
        return false;
    }




    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    private void renderPan() {
        if (this.timeToCameraPositionTarget > 0) {
            this.timeToCameraPositionTarget -= Gdx.graphics.getDeltaTime();
            float progress = timeToCameraPositionTarget < 0 ? 1 : 1f - timeToCameraPositionTarget / panduration;
            float x = Interpolation.pow3Out.apply(xcameraori, xcameradest, progress);
            float y = Interpolation.pow3Out.apply(ycameraori, ycameradest, progress);
            this.worldStage.getCamera().position.set(x, y, 0);
            this.worldStage.getCamera().update();
        }
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT && GameClass.menuPassed) {
            player.fire(this.viewport.getCamera().unproject(new Vector3(screenX,screenY,0)));
        }
        if (!GameClass.menuPassed & button == Input.Buttons.LEFT){
            if (screenX>500 & screenX <800 & screenY >280 & screenY<430){
                GameClass.menuPassed=true;
            }
        }
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.RIGHT) {
            this.rightButtonHold = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (this.rightButtonHold) {
            float xtranslate = this.originMoveCam.sub(screenX, screenY, 0).scl(((OrthographicCamera) this.viewport.getCamera()).zoom).x;
            float ytranslate = -this.originMoveCam.y * 0.5f * this.viewport.getScreenWidth() / this.viewport.getScreenHeight();
            ((OrthographicCamera) this.viewport.getCamera()).translate(xtranslate, ytranslate);
            this.originMoveCam.set(screenX, screenY, 0);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
