package horrorspace.model;

import horrorspace.parsing.face.Face;
import horrorspace.parsing.vertex.Vertex;
import horrorspace.parsing.face.FaceHandlingPrototype;
import horrorspace.parsing.vertex.VertexHandlingPrototype;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Matt
 */
public class ModelPrototype implements VertexHandlingPrototype, FaceHandlingPrototype {
    private List<Vertex> vertices;
    private List<Face> faces;
    
    /**
     *
     * @param vertices
     */
    @Override
    public void setVertices(List<Vertex> vertices) {
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
    
    @Override
    public Model generateItem() {
        int vaoID = generateVertices();
        int faceID = generateFaces();
        final Model model = new Model(vaoID, faceID, faces.size() * 3);
        return model;
    }

    private int generateVertices() {
        int vertexID = GL15.glGenBuffers();
        final Vertex vertexInfo = vertices.get(0);
        int floatBufferSize = vertices.size() * vertexInfo.getVertexFloatSize();
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(floatBufferSize);
        for(Vertex vertex : vertices){
            vertexBuffer.put(vertex.getVertex());
        }
        vertexBuffer.flip();
        
        //Bind
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexID);
        //Add data to buffer
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_STATIC_DRAW);
        //Define data structure
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, vertexInfo.getVertexStride(), 0);
        GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, vertexInfo.getVertexStride(), vertexInfo.getNormalOffset());
        GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, vertexInfo.getVertexStride(), vertexInfo.getTextureOffset());
        //Unbind
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        
        return vaoID;
    }

    private int generateFaces() {
        int faceId = GL15.glGenBuffers();
        IntBuffer faceBuffer = BufferUtils.createIntBuffer(faces.size() * 3);
        for(Face face : faces){
            for(int index : face.getIndices()){
                faceBuffer.put(index);
            }
        }
        faceBuffer.flip();
        
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, faceId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, faceBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        return faceId;
    }
}
