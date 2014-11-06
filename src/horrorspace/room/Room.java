/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room;

import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Matt
 */
public class Room {
    List<Vector3f> vertices;
    List<Face> faces;
    
    /**
     *
     * @param vertices
     */
    public void setVertices(List<Vector3f> vertices) {
        this.vertices = vertices;
    }
    
    /**
     *
     * @param faces
     */
    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    public void render() {
        
        GL11.glBegin(GL11.GL_TRIANGLES);
        for(Face face : faces){
            face.render(vertices);
        }
        GL11.glEnd();
    }
    
}
