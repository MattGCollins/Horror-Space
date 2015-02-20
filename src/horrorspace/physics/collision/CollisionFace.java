package horrorspace.physics.collision;

import horrorspace.parsing.face.Face;
import horrorspace.parsing.vertex.RenderVertex;
import static horrorspace.math.HorrorMath.*;
import java.util.List;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Matt
 */
public class CollisionFace implements CollisionObject {
    Vector3f [] points = {new Vector3f(), new Vector3f(), new Vector3f()};
    Vector3f normal = new Vector3f();
    public static final int WITHIN = 0;
    public static final int OUTSIDE_1 = 1;
    public static final int OUTSIDE_2 = 2;
    public static final int OUTSIDE_3 = 4;
    
    public CollisionFace(Face face, List<RenderVertex> vertices) {
        int[] indices = face.getIndices();
        for(int i = 0; i < indices.length; i++){
            RenderVertex vertex = vertices.get(indices[i]);
            points[i].set(vertex.getX(), vertex.getY(), vertex.getZ());
        }
        Vector3f vector1 = sub(points[0], points[1]);
        Vector3f vector2 = sub(points[2], points[1]);
        crossProduct(vector1, vector2).normalise(normal);
    }

    @Override
    public void pushAwayPrimary(CollisionObject object) {
        if(object instanceof CollisionSphere){
            CollisionSphere sphere = (CollisionSphere) object;
            pushAwayPrimary(sphere);
        }
    }
    
    private void pushAwayPrimary(CollisionSphere sphere) {
        Vector3f vectorTo = sub(sphere.getPosition(), points[0]);
        float distance = dotProduct(vectorTo, normal);
        float radius = sphere.getRadius();
        if(Math.abs(distance) >= radius){
            //We're too far away.  Return.
            return;
        }
        //We're within the face's plane
        int collisionResult = isWithinFace(sphere.getPosition());
        if(collisionResult == WITHIN) {
            float scale =  getSign(distance) * (radius - Math.abs(distance));
            sphere.rebound(scaleVector(normal, scale));
            //Collided with the side!
        }
    }

    @Override
    public String toString() {
        return stringVector(points[0]) + ", " + stringVector(points[1]) + ", " + stringVector(points[2]) + " | Normal = " + stringVector(normal); 
    }

    private int isWithinFace(Vector3f point) {
        int returnValue = WITHIN;
        if(isNotWithinSide(points[0], points[1], point)) { returnValue += OUTSIDE_1; }
        if(isNotWithinSide(points[1], points[2], point)) { returnValue += OUTSIDE_2; }
        if(isNotWithinSide(points[2], points[0], point)) { returnValue += OUTSIDE_3; }
        return returnValue;
    }

    private boolean isNotWithinSide(Vector3f sidePoint1, Vector3f sidePoint2, Vector3f point) {
        Vector3f cross = crossProduct(sub(sidePoint1, sidePoint2), normal);
        Vector3f vectorTo = sub(point, sidePoint1);
        return dotProduct(cross, vectorTo) >= 0;
    }

    private void collideSidesAndPoints(int collisionResult, CollisionSphere sphere) {
        switch(collisionResult){
            case OUTSIDE_1:
                collideSide(points[0], points[1], sphere);
                break;
            case OUTSIDE_2:
                collideSide(points[1], points[2], sphere);
                break;
            case OUTSIDE_3:
                collideSide(points[2], points[0], sphere);
                break;
            case OUTSIDE_1 | OUTSIDE_2:
                collidePoint(points[1], sphere);
                break;
            case OUTSIDE_1 | OUTSIDE_3:
                collidePoint(points[0], sphere);
                break;
            case OUTSIDE_2 | OUTSIDE_3:
                collidePoint(points[2], sphere);
                break;
        }
    }

    private void collideSide(Vector3f point1, Vector3f point2, CollisionSphere sphere) {
        Vector3f side = new Vector3f();
        Vector3f spherePosition = sphere.getPosition();
        sub(point1, point2).normalise(side);
        Vector3f vectorTo = sub(spherePosition, point2);
        Vector3f closestPoint = add(scaleVector(side, dotProduct(side, vectorTo)), point2);
        collidePoint(closestPoint, sphere);
    }

    private void collidePoint(Vector3f point, CollisionSphere sphere) {
        Vector3f spherePosition = sphere.getPosition();
        float squaredDistance = distanceSquared(point, spherePosition);
        if(squaredDistance < (sphere.getRadius() * sphere.getRadius())) {
            float scale = (sphere.getRadius() - (float) Math.sqrt(squaredDistance));
            Vector3f direction = sub(spherePosition, point);
            direction.normalise(direction);
            sphere.rebound(scaleVector(direction, scale));
        }
    }

    @Override
    public void pushAwaySecondary(CollisionObject object) {
        if(object instanceof CollisionSphere){
            CollisionSphere sphere = (CollisionSphere) object;
            pushAwaySecondary(sphere);
        }
    }
    
    public void pushAwaySecondary(CollisionSphere sphere) {
        Vector3f vectorTo = sub(sphere.getPosition(), points[0]);
        float distance = dotProduct(vectorTo, normal);
        float radius = sphere.getRadius();
        if(Math.abs(distance) >= radius){
            //We're too far away.  Return.
            return;
        }
        int collisionResult = isWithinFace(sphere.getPosition());
        if(collisionResult == WITHIN) {
            float scale =  getSign(distance) * (radius - Math.abs(distance));
            sphere.rebound(scaleVector(normal, scale));
            //Collided with the side.  Return now!
            return;
        }
        collideSidesAndPoints(collisionResult, sphere);
    }
    
    
}
