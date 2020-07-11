package Utilities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BodyFactory {
    private static World world;
    public static void initializeFactory(World world){
        BodyFactory.world=world;
    }

    public static void createSensor(float x, float y, float width, float height,Body body){
        FixtureDef fixDef = new FixtureDef();
        fixDef.isSensor =true;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2  ,height/2,new Vector2(x,y),0);
        fixDef.shape=shape;
        body.createFixture(fixDef);
    }


    public static Body createBody(float x, float y, float width, float height,String bodyType,boolean fixedRotation){
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = fixedRotation;
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
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        bodyd.createFixture(shape, 0.0f);
        return bodyd;
    }

    public static Body createBody(Rectangle rectangle,String bodyType,boolean fixedRotation){
        return createBody((int)(rectangle.x),(int)(rectangle.y),(int)(rectangle.width),(int)(rectangle.height),bodyType,fixedRotation);
    }
}
