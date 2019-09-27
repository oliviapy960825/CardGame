package edu.udel.dianali.gameframework;

/**
 * Represents a directional vector with up to 3 dimensions.
 * 
 * @author jatlas
 */
public class DirectionVector extends Component3d {

    public DirectionVector(float x, float y) {
        super(x, y);
    }
    
    public DirectionVector(float x, float y, float z) {
        super(x, y, z);
    }
    
    /**
     * The magnitude of the vector (i.e. its length in Cartesian
     * coordinates).
     * 
     * @return
     */
    public float magnitude() {
        return (float)Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    public String toString() {
        return super.toString() + "[magnitude=" + magnitude()+"]";
    }
}
