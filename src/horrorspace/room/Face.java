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
public class Face {
    int [] indices;
    
    public Face(int [] indices) {
        this.indices = indices;
    }

    void render(List<Vector3f> vertices) {
        GL11.glTexCoord2d(0,0);
        GL11.glVertex3f(vertices.get(indices[0]).x, vertices.get(indices[0]).y, vertices.get(indices[0]).z);
        GL11.glTexCoord2d(1,0);
        GL11.glVertex3f(vertices.get(indices[1]).x, vertices.get(indices[1]).y, vertices.get(indices[1]).z);
        GL11.glTexCoord2d(1,1);
        GL11.glVertex3f(vertices.get(indices[2]).x, vertices.get(indices[2]).y, vertices.get(indices[2]).z);
    }
}
