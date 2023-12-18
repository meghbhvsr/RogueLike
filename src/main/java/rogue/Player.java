package rogue;
import java.awt.Point;
import java.io.Serializable;
/**.
 * The player character
 */
public class Player implements Serializable {
    private String name;
    private Point newXyLocation;
    private Room newRoom;


    // Default constructor
    /**.
     *default
     */
    public Player() {
        this.name = new String();
        this.newXyLocation = new Point();
    }

    /**
     * @param name2
     */
    public Player(String name2) {
        this.name = name2;
        this.newXyLocation = new Point(1, 1);
    }
    /**.
     *@return String
     */
    public String getName() {

        return this.name;
    }
    /**
     * @param newName
     */
    public void setName(String newName) {
        this.name = newName;
    }
    /**.
     *@return Point
     */
    public Point getXyLocation() {
        return newXyLocation;
    }
    /**
     * @param newXyLocation2
     */
    public void setXyLocation(Point newXyLocation2) {
        this.newXyLocation = newXyLocation2;
    }

    /**.
     *@return Room
     */
    public Room getCurrentRoom() {
        return this.newRoom;

    }

    /**
     * @param newRoom2
     */
    public void setCurrentRoom(Room newRoom2) {
        this.newRoom = newRoom2;
    }
}


