package horrorspace.physics.collision.parsing;

import horrorspace.physics.collision.CollisionFace;
import horrorspace.physics.collision.CollisionModel;
import horrorspace.parsing.face.Face;
import horrorspace.parsing.vertex.RenderVertex;
import horrorspace.parsing.face.FaceHandlingPrototype;
import horrorspace.parsing.vertex.VertexHandlingPrototype;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matt
 */
public class CollisionModelPrototype implements VertexHandlingPrototype, FaceHandlingPrototype {
    private List<RenderVertex> vertices;
    private List<Face> faces;
    
    /**
     *
     * @param vertices
     */
    @Override
    public void setVertices(List<RenderVertex> vertices) {
        this.vertices = vertices;
    }
    
    /**
     *
     * @param faces
     */
    @Override
    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }
    
    /**
     *
     * @return
     */
    @Override
    public CollisionModel generateItem() {
        CollisionModel collisionModel = new CollisionModel();
        List<CollisionFace> collisionFaces = new ArrayList<>();
        for(Face face : faces) {
            collisionFaces.add(new CollisionFace(face, vertices));
        }
        collisionModel.setFaces(collisionFaces);
        return collisionModel;
    }
}
