/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Matt
 */
public class Room {
    private final int vaoID;
    private final int vertexID;
    private final int textureID;
    private final int faceID;
    private final int indices;
    

    public Room(int vaoID, int vertexID, int textureID, int faceID, int indices) {
        this.vaoID = vaoID;
        this.vertexID = vertexID;
        this.textureID = textureID;
        this.faceID = faceID;
        this.indices = indices;
    }

    public void render() {
        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);
        

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, faceID);

        // Draw the vertices
        GL11.glDrawElements(GL11.GL_TRIANGLES, indices, GL11.GL_UNSIGNED_INT, 0);

        // Put everything back to default (deselect)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
    
}
