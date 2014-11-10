package horrorspace.model;

import horrorspace.Resources;
import horrorspace.rendering.Shaders;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 *
 * @author Matt
 */
public class Model {
    private final int vaoID;
    private final int faceID;
    private final int indices;
    

    public Model(int vaoID, int faceID, int indices) {
        this.vaoID = vaoID;
        this.faceID = faceID;
        this.indices = indices;
    }

    public void render() {
        glUseProgram(Shaders.programId);
        glBindTexture(GL_TEXTURE_2D, Resources.textures[1].getTextureID());
        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, faceID);

        // Draw the vertices
        glDrawElements(GL_TRIANGLES, indices, GL_UNSIGNED_INT, 0);

        // Put everything back to default (deselect)
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
        glUseProgram(0);
    }
    
}
