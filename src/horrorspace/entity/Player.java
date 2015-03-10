package horrorspace.entity;

import horrorspace.Globals;
import horrorspace.engine.InputKeeper;
import horrorspace.math.HorrorMath;
import horrorspace.physics.collision.CollisionObject;
import horrorspace.physics.collision.CollisionSphere;
import java.awt.Point;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/**
 *
 * @author Elle
 */
public class Player extends Entity implements CollisionSphere {
    public int currentMove;
    public InputKeeper input;
    public float jumpPosition;
    private float rotationX;
    private float rotationY;
    private Quaternion rotationalBase;
    private Quaternion rotation;
    private Vector3f previousGravity;
    
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
        rotationalBase = new Quaternion();
        rotation = rotationalBase;
        previousGravity = null;
    }
    
    public void init() {
        Globals.collisionManager.addEntity(this);
    }
    
    @Override
    public void update()
    {
        checkGravityOrientation();
        calculateRotation();
        addTranslation(calculateTranslation());
        handleSpecialInputs();
        super.update();
    }
    
    public void handleSpecialInputs()
    {
        if(input.isDown(Keyboard.KEY_F4)){
            if(! mouseToggling){
                mouseCaptured = !mouseCaptured;
                input.captureMouse(mouseCaptured);
                mouseToggling = true;
            }
        } else {
            mouseToggling = false;
        }
    }

    private void calculateRotation() {
        Point mouseDiff = input.getMouseDiff();
        rotationX += mouseDiff.x / 5.0f;
        if(rotationX > 180){
            rotationX -= 360;
        } else if (rotationX < -180) {
            rotationX += 360;
        }
        rotationY += mouseDiff.y / 5.0f;
        if(rotationY > 90){
            rotationY = 90;
        } else if (rotationY < -90) {
            rotationY = -90;
        }
        Quaternion pitchQuaternion = new Quaternion();
        pitchQuaternion.setFromAxisAngle(new Vector4f(-1, 0, 0, (float) (rotationY * (Math.PI / 180.0f))));
        Quaternion yawQuaternion = new Quaternion();
        yawQuaternion.setFromAxisAngle(new Vector4f(0, 1, 0, (float) (rotationX * (Math.PI / 180.0f))));
        Quaternion addedRotation = new Quaternion();
        Quaternion.mul(pitchQuaternion, yawQuaternion, rotation);
        Quaternion.mul(rotation, rotationalBase, rotation);
        rotation.normalise();
    }

    private Vector3f calculateTranslation() {
        float moveSpeed = isGrounded() ? GROUND_MOVE_SPEED : AIR_MOVE_SPEED;
        Vector3f translation = new Vector3f(0, 0, 0);
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
            if(jumpPosition == 0.0f){
                if(isGrounded()){
                    if(jumpPosition == 0.0f){
                        translation.y += JUMP_INITIAL_BOOST;
                    }
                    translation.y += JUMP_HOLD_BOOST;
                    jumpPosition += Globals.frameElapsed;
                }
            } else if (jumpPosition <= JUMP_DURATION) {
                translation.y += JUMP_HOLD_BOOST;
                jumpPosition += Globals.frameElapsed;
            }
        } else {
            jumpPosition = 0.0f;
        }
        return translation;
    }

    public void addTranslation(Vector3f translation) {
        Quaternion yawQuaternion = new Quaternion();
        yawQuaternion.setFromAxisAngle(new Vector4f(0, 1, 0, (float) (rotationX * (Math.PI / 180.0f))));
        Quaternion rotationQuaternion = new Quaternion();
        Quaternion.mul(yawQuaternion, rotationalBase, rotationQuaternion);
        rotationQuaternion.normalise();
        Vector3f translationRotated = HorrorMath.rotateVectorByQuaternion(translation, rotationQuaternion);
        accelerate(translationRotated);
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
    
    public Quaternion getRotation() {
        return rotation;
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

    private void checkGravityOrientation() {
        final Vector3f currentGravity = getGravity();
        if(previousGravity != currentGravity){
            previousGravity = currentGravity;
            if(currentGravity != null){
                recalculateRotationalBase(rotation, currentGravity);
            }
        }
    }

    private void recalculateRotationalBase(Quaternion rotationalStart, Vector3f currentGravity) {
        Vector3f newUp = new Vector3f();
        currentGravity.negate(newUp);
        newUp.normalise();
        Vector3f originalBackward = HorrorMath.rotateVectorByQuaternion(new Vector3f(0, 0, 1), rotationalStart);
        Vector3f newRight = HorrorMath.crossProduct(newUp, originalBackward);
        if(newRight.length() < 0.001) {
            if(HorrorMath.dotProduct(originalBackward, newUp) > 0){
                originalBackward = HorrorMath.rotateVectorByQuaternion(new Vector3f(0, 1, 0), rotationalStart);
            } else {
                originalBackward = HorrorMath.rotateVectorByQuaternion(new Vector3f(0, -1, 0), rotationalStart);
            }
            newRight = HorrorMath.crossProduct(newUp, originalBackward);
        }
        Vector3f newBack = HorrorMath.crossProduct(newRight, newUp);
        Matrix3f newMatrixRotation = new Matrix3f();
        newMatrixRotation.m00 = newRight.x;
        newMatrixRotation.m01 = newRight.y;
        newMatrixRotation.m02 = newRight.z;
        newMatrixRotation.m10 = newUp.x;
        newMatrixRotation.m11 = newUp.y;
        newMatrixRotation.m12 = newUp.z;
        newMatrixRotation.m20 = newBack.x;
        newMatrixRotation.m21 = newBack.y;
        newMatrixRotation.m22 = newBack.z;
        Quaternion newQuaternionRotation = new Quaternion();
        newQuaternionRotation.setFromMatrix(newMatrixRotation);
        newQuaternionRotation.normalise();
        rotationalBase = newQuaternionRotation;
        rotationX = 0;
    }
}
