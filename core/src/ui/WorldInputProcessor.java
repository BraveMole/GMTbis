package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import panic.game.World;

public class WorldInputProcessor implements InputProcessor {

    public static float worldZoom;
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


    public WorldInputProcessor(Viewport viewport, World stage) {
        this.worldStage = stage;
        Gdx.input.setInputProcessor(this);
        this.viewport = viewport;
        this.originMoveCam = new Vector3();
    }


    public void act() {
        this.renderZoom();
        this.renderPan();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }


    private void renderZoom() {
        if (timeToCameraZoomTarget >= 0) {
            timeToCameraZoomTarget -= Gdx.graphics.getDeltaTime();
            float progress = timeToCameraZoomTarget < 0 ? 1 : 1f - timeToCameraZoomTarget / zoomduration;
            ((OrthographicCamera) this.viewport.getCamera()).zoom = Interpolation.pow3Out.apply(cameraZoomOrigin, cameraZoomTarget, progress);
            worldZoom = ((OrthographicCamera) this.viewport.getCamera()).zoom;
        }
    }

    private void zoomBy(float zoomAmount) {
        this.zoomTo(cameraZoomTarget * (zoomAmount));
    }

    private void zoomTo(float zoomAmount) {
        cameraZoomOrigin = ((OrthographicCamera) this.viewport.getCamera()).zoom;
        worldZoom = cameraZoomOrigin;
        cameraZoomTarget = zoomAmount;
        timeToCameraZoomTarget = zoomduration;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {

        this.zoomBy(1 + amount * 0.2f);
        this.viewport.getCamera().update();
        return true;
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

    private void panTo(float x, float y) {
        this.timeToCameraPositionTarget = this.panduration;
        this.xcameradest = x;
        this.ycameradest = y;
        this.xcameraori = this.worldStage.getCamera().position.x;
        this.ycameraori = this.worldStage.getCamera().position.y;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.RIGHT) {
            this.rightButtonHold = true;
            originMoveCam.set(screenX, screenY, 0);
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
}
