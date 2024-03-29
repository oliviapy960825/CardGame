package edu.udel.dianali.gameframework;

/**
 * A class representing a Position with up to 3 dimensions.
 * Supports 2 dimensions by using value 0 for the 3rd dimension.
 * Also supports integer based coordinates by providing wrapper
 * methods for getters/setters on row, column, and depth.
 * 
 * @author jatlas
 *
 */
public class Position extends Component3d {
    public Position(float x, float y) {
        super(x, y);
    }

    public Position(float x, float y, float z) {
        super(x, y, z);
    }

    /**
     * @return the column as the containing grid column 
     *  (i.e. the x value as an int).
     */
    public int getColumn() {
        return (int)getX();
    }
    
    /**
     * @return the row as the containing grid row 
     *  (i.e. the y value as an int).
     */
    public int getRow() {
        return (int)getY();
    }
    
    /**
     * @return the depth as the containing grid depth 
     *  (i.e. the z value as an int).
     */
    public int getDepth() {
        return (int)getZ();
    }
    
    public void setColumn(int column) {
        setX(column);
    }
    
    public void setRow(int row) {
        setY(row);
    }
    
    public void setDepth(int depth) {
        setZ(depth);
    }
    
    /**
     * Computes the distance "as the crow flies" between two Positions
     * in a Cartesian coordinate system.
     * 
     * @param other
     * @return
     */
    public float distance(Position other) {
        float dx = other.getX() - getX();
        float dy = other.getY() - getY();
        float dz = other.getZ() - getZ();
        return (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    
    /**
     * Computes the distance in grid spaces between two Positions as
     * would be required to "walk" along city blocks. This is also
     * known in game design literature as Manhattan block distance.
     * 
     * Because this uses the int accessor methods, if we have:
     * 
     * Position a = new Position(3.999, 2.0);
     * Position b = new Position(4.0, 1.999);
     * 
     * a.blockDistance(b) -> 2
     * 
     * whereas a.distance(b) -> 0.0014142
     * 
     * @param other
     * @return
     */
    public int blockDistance(Position other) {
        int dx = Math.abs(other.getColumn() - getColumn());
        int dy = Math.abs(other.getRow() - getRow());
        int dz = Math.abs(other.getDepth() - getDepth());
        return dx + dy + dz;
    }
    
    /**
     * Mutates this Position by a scaling of the given vector.
     * Calls {@link #move(DirectionVector, float)} with a scaling of 100% 
     * of the vector (1.0).
     * 
     * @param v
     */
    public void move(DirectionVector v)
    {
        move(v, 1.0f);
    }
    
    /**
     * Mutates this Position by a scaling of the given vector.
     * An example of using apply: 
     * 
     * Position p = new Position(1, 1, 1);
     * DirectionVector v = new DirectionVector(2, -1, 1);
     * p.move(v, 0.5);
     * p.getX() -> 2.0
     * p.getY() -> 0.5
     * p.getZ() -> 1.5
     * 
     * @param v
     * @param scaling
     * @return
     */
    public void move(DirectionVector v, float scaling) {
        // your code goes here
        this.x += v.getX()*scaling;
        this.y += v.getY()*scaling;
        this.z += v.getZ()*scaling;
    }
}
