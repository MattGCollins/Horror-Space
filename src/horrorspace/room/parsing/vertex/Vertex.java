/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing.vertex;

/**
 *
 * @author Matt
 */
public class Vertex {
    private float [] position = new float[3];
    private float [] normal = new float[3];
    private float [] texture = new float[2];
    private boolean normalExists = false;
    private boolean textureExists = false;

    /**
     * @return get the x position of the vertex
     */
    public float getX() {
        return position[0];
    }

    /**
     * @param x set the x position of the vertex
     */
    public void setX(float x) {
        position[0] = x;
    }

    /**
     * @return get the y position of the vertex
     */
    public float getY() {
        return position[1];
    }

    /**
     * @param y set the y position of the vertex
     */
    public void setY(float y) {
        position[1] = y;
    }

    /**
     * @return get the z position of the vertex
     */
    public float getZ() {
        return position[2];
    }

    /**
     * @param z set the z position of the vertex
     */
    public void setZ(float z) {
        position[2] = z;
    }

    /**
     * @return get the x component of the vertex normal
     */
    public float getNx() {
        return normal[0];
    }

    /**
     * @param nx set the x component of the vertex normal
     */
    public void setNx(float nx) {
        normalExists = true;
        normal[0] = nx;
    }

    /**
     * @return get the y component of the vertex normal
     */
    public float getNy() {
        return normal[1];
    }

    /**
     * @param ny set the y component of the vertex normal
     */
    public void setNy(float ny) {
        normal[1] = ny;
    }

    /**
     * @return get the z component of the vertex normal
     */
    public float getNz() {
        return normal[2];
    }

    /**
     * @param nz set the z component of the vertex normal
     */
    public void setNz(float nz) {
        normal[2] = nz;
    }

    /**
     * @return get the s of the vertex texture coordinate
     */
    public float getS() {
        return texture[0];
    }

    /**
     * @param s set the s of the vertex texture coordinate
     */
    public void setS(float s) {
        textureExists = true;
        texture[0] = s;
    }

    /**
     * @return get the t of the vertex texture coordinate
     */
    public float getT() {
        return texture[1];
    }

    /**
     * @param t set the t of the vertex texture coordinate
     */
    public void setT(float t) {
        texture[1] = t;
    }

    /**
     * @return the normalExists
     */
    public boolean isNormalExists() {
        return normalExists;
    }

    /**
     * @return the textureExists
     */
    public boolean isTextureExists() {
        return textureExists;
    }

    /**
     * @return get the position array
     */
    public float[] getPosition() {
        return position;
    }

    /**
     * @return get the normal array
     */
    public float[] getNormal() {
        return normal;
    }

    /**
     * @return get the texture array
     */
    public float[] getTexture() {
        return texture;
    }
    

    /**
     * @return get an array with all data
     */
    public float[] getVertex(){
        int floatSize = 3 + (normalExists ? 3 : 0) + (textureExists ? 2 : 0);
        float [] vertex = new float[floatSize];
        
        int copyPosition = 0;
        System.arraycopy(position, 0, vertex, copyPosition, 3);
        copyPosition += 3;
        
        if(normalExists){
            System.arraycopy(normal, 0, vertex, copyPosition, 3);
            copyPosition += 3;
        }
        
        if(textureExists){
            System.arraycopy(texture, 0, vertex, copyPosition, 2);
        }
        
        return vertex;
    }
    
}
