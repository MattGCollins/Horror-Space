package horrorspace.entity;

import horrorspace.Globals;
import horrorspace.engine.InputKeeper;
import horrorspace.physics.collision.CollisionObject;
import horrorspace.physics.collision.CollisionSphere;
import java.awt.Point;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Elle
 */
public class Player extends Entity implements CollisionSphere {
    private static final double MOVE_SPEED = 0.04;
    
    public int currentMove;
    public InputKeeper input;
    
    //Remove this once menus come into play
    private boolean mouseCaptured = true;
    
    public static final float movementTime = 0.4f;
    public float curMovementTime = 0.0f;
    
    public Player()
    {
        input = Globals.input;
        moveTo(0.0f, 1.0f, 0.0f);
        setKeyBindings();
        Globals.collisionManager.addEntity(this);
    }
    
    @Override
    public void update()
    {
        super.update();
        handleInput();
    }
    
    public void handleInput()
    {
        if(input.isDown(Keyboard.KEY_A) || input.isDown(Keyboard.KEY_LEFT)){
            position.x -= MOVE_SPEED;
        }
        if(input.isDown(Keyboard.KEY_D) || input.isDown(Keyboard.KEY_RIGHT)){
            position.x += MOVE_SPEED;
        }
        if(input.isDown(Keyboard.KEY_S) || input.isDown(Keyboard.KEY_DOWN)){
            position.z += MOVE_SPEED;
        }
        if(input.isDown(Keyboard.KEY_W) || input.isDown(Keyboard.KEY_UP)){
            position.z -= MOVE_SPEED;
        }
        if(input.isDown(Keyboard.KEY_E)){
            position.y += MOVE_SPEED;
        }
        if(input.isDown(Keyboard.KEY_Q)){
            position.y -= MOVE_SPEED;
        }
        if(input.isDown(Keyboard.KEY_F4)){
            mouseCaptured = !mouseCaptured;
            input.captureMouse(mouseCaptured);
        }
        Point mouseDiff = input.getMouseDiff();
        rotation += mouseDiff.x * 0.3;
        
    }

    private void setKeyBindings() {
        input.captureMouse(true);
        input.addCheck(Keyboard.KEY_UP);
        input.addCheck(Keyboard.KEY_DOWN);
        input.addCheck(Keyboard.KEY_LEFT);
        input.addCheck(Keyboard.KEY_RIGHT);
        input.addCheck(Keyboard.KEY_W);
        input.addCheck(Keyboard.KEY_S);
        input.addCheck(Keyboard.KEY_A);
        input.addCheck(Keyboard.KEY_D);
        input.addCheck(Keyboard.KEY_Q);
        input.addCheck(Keyboard.KEY_E);
        input.addCheck(Keyboard.KEY_X);
        input.addCheck(Keyboard.KEY_M);
        input.addCheck(Keyboard.KEY_F4);
        input.addCheck(Keyboard.KEY_TAB);
    }

    @Override
    public void collideWithStatic(Entity entity) {
        entity.getCollisionObject().pushAway(null);
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public float getRadius() {
        return 0.5f;
    }

    @Override
    public void pushAway(CollisionObject object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPosition(Vector3f vector) {
        position = vector;
    }
}
