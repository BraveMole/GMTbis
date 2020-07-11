package Utilities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class BodyFactory {
    private static World world;
    public static void initializeFactory(World world){
        BodyFactory.world=world;
    }

    public static Body createBody(int x, int y, int width, int height,String bodyType,float friction,float density){
        BodyDef bodyDef = new BodyDef();
        switch (bodyType){
            case "Static":
                bodyDef.type = BodyDef.BodyType.StaticBody;
                break;
            default:
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                break;
        }

        bodyDef.position.set(x,y);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2  ,height/2);
        Body bodyd = world.createBody(bodyDef);
        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        bodyd.createFixture(shape, 0.0f);
        return bodyd;
    }

    public static Body createBody(Rectangle rectangle,String bodyType,float friction,float density){
        return createBody((int)(rectangle.x),(int)(rectangle.y),(int)(rectangle.width),(int)(rectangle.height),bodyType,friction,density);
    }
}
