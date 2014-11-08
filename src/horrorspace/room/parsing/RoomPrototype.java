/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing;

import horrorspace.room.Room;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Matt
 */
public class RoomPrototype {
    private List<Vector3f> vertices;
    private List<Face> faces;
    private int vaoID;
    
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
    
    public Room generateRoom() {
        vaoID = GL30.glGenVertexArrays();
        int vertexID = generateVertices();
        int faceID = generateFaces();
        int colorID = generateColors();
        final Room room = new Room(vaoID, vertexID, colorID, faceID, faces.size() * 3);
        return room;
    }

    private int generateVertices() {
        int vertexID = GL15.glGenBuffers();
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.size() * 3);
        for(Vector3f vertex : vertices){
            vertexBuffer.put(vertex.x);
            vertexBuffer.put(vertex.y);
            vertexBuffer.put(vertex.z);
        }
        vertexBuffer.flip();
        
        //Bind
        GL30.glBindVertexArray(vaoID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexID);
        //Add data to buffer
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        //Unbind
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        
        return vertexID;
    }
    
    private int generateColors() {
        int colorID = GL15.glGenBuffers();
        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(vertices.size() * 4);
        for(Vector3f vertex : vertices){
            colorBuffer.put(1.0f);
            colorBuffer.put(1.0f);
            colorBuffer.put(1.0f);
            colorBuffer.put(1.0f);
        }
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        return colorID;
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
