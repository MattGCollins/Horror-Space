/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.entity;

import horrorspace.Globals;
import horrorspace.engine.InputKeeper;
import java.awt.Point;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Elle
 */
public class Player extends Entity{
    private static final double MOVE_SPEED = 0.04;
    
    public int currentMove;
    public InputKeeper input;
    
    public static final float movementTime = 0.4f;
    public float curMovementTime = 0.0f;
    
    public Player()
    {
        input = Globals.input;
        moveTo(0.0f, 1.0f, 0.0f);
        setKeyBindings();
    }
    
    @Override
    public void update()
    {
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
        input.addCheck(Keyboard.KEY_TAB);
    }
}
