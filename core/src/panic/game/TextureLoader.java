package panic.game;

import actors.Plateform;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureLoader {
    public static Texture BigGrass;
    public static Texture Player;
    public static Texture[] W = new Texture[2];
    public static Texture[] A = new Texture[2];
    public static Texture[] S = new Texture[2];
    public static Texture[] D = new Texture[2];

    public static void loadTexture() {
        loadTextureEnvironment();
        loadTextureActors();
    }

    private static void loadTextureActors(){
        Player = new Texture("Triston.png");
        W[0] = new Texture("W.png");
        W[1] = new Texture("W_no.png");
        A[0] = new Texture("A.png");
        A[1] = new Texture("A_no.png");
        S[0] = new Texture("S.png");
        S[1] = new Texture("S_no.png");
        D[0] = new Texture("D.png");
        D[1] = new Texture("D_no.png");
    }


    private static void loadTextureEnvironment() {
        BigGrass = new Texture("BIG_grass.png");
    }
}
