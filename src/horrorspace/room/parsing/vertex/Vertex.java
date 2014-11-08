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
    private float x;
    private float y;
    private float z;
    private float nx;
    private float ny;
    private float nz;
    private float s;
    private float t;

    /**
     * @return get the x position of the vertex
     */
    public float getX() {
        return x;
    }

    /**
     * @param x set the x position of the vertex
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return get the y position of the vertex
     */
    public float getY() {
        return y;
    }

    /**
     * @param y set the y position of the vertex
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return get the z position of the vertex
     */
    public float getZ() {
        return z;
    }

    /**
     * @param z set the z position of the vertex
     */
    public void setZ(float z) {
        this.z = z;
    }

    /**
     * @return get the x component of the vertex normal
     */
    public float getNx() {
        return nx;
    }

    /**
     * @param nx set the x component of the vertex normal
     */
    public void setNx(float nx) {
        this.nx = nx;
    }

    /**
     * @return get the y component of the vertex normal
     */
    public float getNy() {
        return ny;
    }

    /**
     * @param ny set the y component of the vertex normal
     */
    public void setNy(float ny) {
        this.ny = ny;
    }

    /**
     * @return get the z component of the vertex normal
     */
    public float getNz() {
        return nz;
    }

    /**
     * @param nz set the z component of the vertex normal
     */
    public void setNz(float nz) {
        this.nz = nz;
    }

    /**
     * @return get the s of the vertex texture coordinate
     */
    public float getS() {
        return s;
    }

    /**
     * @param s set the s of the vertex texture coordinate
     */
    public void setS(float s) {
        this.s = s;
    }

    /**
     * @return get the t of the vertex texture coordinate
     */
    public float getT() {
        return t;
    }

    /**
     * @param t set the t of the vertex texture coordinate
     */
    public void setT(float t) {
        this.t = t;
    }
    
}
