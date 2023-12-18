package rogue;
import java.awt.Point;
import java.io.Serializable;

/**.
 * A basic Item class; basic functionality for both consumables and equipment.
 */
public class Item implements Serializable {
    private int id;
    private String name;
    private String type;
    private Point xyLocation;
    private Character newDisplayCharacter;
    private String newDescription;
    private Room newCurrentRoom;
    private String symbolName;
    private String symbol;
    private int roomId;
    //Constructors
    /**.
     *default
     */
    public Item() {
        this.id = 0;
        this.name = new String();
        this.type = new String();
        this.xyLocation = new Point();
    }
    /**
     * @param id2
     * @param name2
     * @param type2
     * @param xyLocation2
     */
    public Item(int id2, String name2, String type2, Point xyLocation2) {
        this.id = id2;
        this.name = name2;
        this.type = type2;
        this.xyLocation = xyLocation2;
    }
    /**
    *@return ArrayList
    */
    public int getRoomId() {
        return this.roomId;
    }
    /**
    *@param id2
    */
    public void setRoomId(int id2) {
        this.roomId = id;
    }
    // Getters and setters
    /**.
     *@return int
     */
    public int getId() {
        return this.id;
    }
    /**
     * @param id2
     */
    public void setId(int id2) {
        this.id = id2;
    }
    /**.
     *@return String
     */
    public String getName() {
        return this.name;
    }
    /**
     * @param name2
     */
    public void setName(String name2) {
        this.name = name2;
    }
    /**.
     *@return String
     */
    public String getType() {
        return type;
    }
    /**
     * @param type2
     */
    public void setType(String type2) {
        this.type = type2;
    }
    /**.
     *@return Character
     */
    public Character getDisplayCharacter() {
        return newDisplayCharacter;
    }
    /**
     * @param newDisplayCharacter2
     */
    public void setDisplayCharacter(Character newDisplayCharacter2) {
        this.newDisplayCharacter = newDisplayCharacter2;
    }
    /**.
     *@return String
     */
    public String getDescription() {
        return this.newDescription;
    }
    /**
     * @param newDescription2
     */
    public void setDescription(String newDescription2) {
        this.newDescription = newDescription2;
    }
    /**.
     *@return Point
     */
    public Point getXyLocation() {
        return xyLocation;
    }
    /**
     * @param newXyLocation
     */
    public void setXyLocation(Point newXyLocation) {
        this.xyLocation = newXyLocation;
    }
    /**.
     *@return Room
     */
    public Room getCurrentRoom() {
        return newCurrentRoom;
    }
    /**
     * @param newCurrentRoom2
     */
    public void setCurrentRoom(Room newCurrentRoom2) {
        this.newCurrentRoom = newCurrentRoom2;
    }
}

