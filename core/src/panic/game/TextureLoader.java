package panic.game;

import actors.Plateform;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureLoader {
    public static Texture BigGrass;
    public static Texture Player;
    public static int WIDTH = 20;
    public static int HEIGHT = 20;
    public static Texture[] KEYS = new Texture[8];

    public static void loadTexture() {
        loadTextureEnvironment();
        loadTextureActors();
    }

    private static void loadTextureActors(){
        Player = new Texture("Triston.png");
        KEYS[0] = new Texture("W.png");
        KEYS[1] = new Texture("W_no.png");
        KEYS[2] = new Texture("A.png");
        KEYS[3] = new Texture("A_no.png");
        KEYS[4] = new Texture("S.png");
        KEYS[5] = new Texture("S_no.png");
        KEYS[6] = new Texture("D.png");
        KEYS[7] = new Texture("D_no.png");
    }


    private static void loadTextureEnvironment() {
        BigGrass = new Texture("BIG_grass.png");
    }
}
