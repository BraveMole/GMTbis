package Utilities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Animation {

    ;

    private float frameDuration;
    private TextureRegion[] keyFrames;
    private float width;
    private float height;

    Animation(float frameDuration, TextureRegion[] keyFrames, float width, float height) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
        this.width = width;
        this.height = height;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public TextureRegion[] getKeyFrames() {
        return keyFrames;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
