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
    public int currentMove;
    public InputKeeper input;
    public float jumpPosition;
    
    //Remove this once menus come into play
    private boolean mouseCaptured = true;
    
    public float curMovementTime = 0.0f;
    private boolean mouseToggling = false;
    
    private static final float JUMP_DURATION = 0.2f;
    private static final float JUMP_INITIAL_BOOST = 5.0f;
    private static final float JUMP_HOLD_BOOST = 0.4f;
    private static final float GROUND_MOVE_SPEED = 0.8f;
    private static final float AIR_MOVE_SPEED = 0.06f;
    
    public Player()
    {
        input = Globals.input;
        setPosition(new Vector3f(0.0f, 1.0f, 0.0f));
        setKeyBindings();
        jumpPosition = 0;
    }
    
    public void init() {
        Globals.collisionManager.addEntity(this);
    }
    
    @Override
    public void update()
    {
        handleInput();
        super.update();
    }
    
    public void handleInput()
    {
        float moveSpeed = isGrounded() ? GROUND_MOVE_SPEED : AIR_MOVE_SPEED;
        Vector3f translation = new Vector3f();
        if(input.isDown(Keyboard.KEY_A) || input.isDown(Keyboard.KEY_LEFT)){
            translation.x -= moveSpeed;
        }
        if(input.isDown(Keyboard.KEY_D) || input.isDown(Keyboard.KEY_RIGHT)){
            translation.x += moveSpeed;
        }
        if(input.isDown(Keyboard.KEY_S) || input.isDown(Keyboard.KEY_DOWN)){
            translation.z += moveSpeed;
        }
        if(input.isDown(Keyboard.KEY_W) || input.isDown(Keyboard.KEY_UP)){
            translation.z -= moveSpeed;
        }
        if(input.isDown(Keyboard.KEY_SPACE)){
            if(jumpPosition <= JUMP_DURATION){
                System.err.println("1");
                if(jumpPosition == 0.0f){
                    translation.y += JUMP_INITIAL_BOOST;
                }
                translation.y += JUMP_HOLD_BOOST;
                jumpPosition += Globals.frameElapsed;
            }
        } else {
            jumpPosition = 0.0f;
        }
        accelerate(translation);
        if(input.isDown(Keyboard.KEY_F4) && ! mouseToggling){
            mouseCaptured = !mouseCaptured;
            input.captureMouse(mouseCaptured);
            mouseToggling = true;
        } else {
            mouseToggling = false;
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
        input.addCheck(Keyboard.KEY_SPACE);
        input.addCheck(Keyboard.KEY_X);
        input.addCheck(Keyboard.KEY_M);
        input.addCheck(Keyboard.KEY_F4);
        input.addCheck(Keyboard.KEY_TAB);
    }

    @Override
    public void collideWithStatic(Entity entity) {
        entity.getCollisionObject().pushAwayPrimary(null);
    }

    @Override
    public float getRadius() {
        return 0.5f;
    }

    @Override
    public void rebound(Vector3f vector) {
        reboundSurface(vector);
    }

    @Override
    public void pushAwayPrimary(CollisionObject object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pushAwaySecondary(CollisionObject object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
