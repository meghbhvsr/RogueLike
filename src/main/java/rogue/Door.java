package rogue;
//import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Point;
import java.io.Serializable;


public class Door implements Serializable {
  private String direction;
  private int conRoom;
  private int position;
  private Point point;
  private Room room;
  private int id;
  private ArrayList<Room> connectedRooms = new ArrayList<Room>();
  /**.
  *default
  */
  public Door() {
  }
  /**.
  *@param newRoom
  */
  public void setRoom(Room newRoom) {
    this.room = newRoom;
  }
  /**.
  *@param newDir
  */
  public void setDirection(String newDir) {
    this.direction = newDir;
  }
  /**.
  *@param newConRoom
  */
  public void setConRoom(int newConRoom) {
    this.conRoom = newConRoom;
  }
  /**.
  *@param newPosition
  */
  public void setPosition(int newPosition) {
    this.position = newPosition;
    if (this.direction.equals("N")) {
        this.point = new Point(this.position, 0);
    } else if (this.direction.equals("S")) {
        this.point = new Point(this.position, this.room.getHeight() - 1);
    } else if (this.direction.equals("E")) {
        this.point = new Point(this.room.getWidth() - 1, this.position);
    } else if (this.direction.equals("W")) {
        this.point = new Point(0, this.position);
    }
  }
  /**.
  *@return String
  */
  public String getDirection() {
    return this.direction;
  }
  /**.
  *@return int
  */
  public int getRoomId() {
    return this.room.getId();
  }
  /**.
  *@return int
  */
  public int getId() {
    return this.id;
  }
  /**.
  *@param newId
  */
  public void setId(int newId) {
    this.id = newId;
  }
  /**.
  *@return int
  */
  public int getConRoom() {
    return this.conRoom;
  }
  /**.
  *@return int
  */
  public int getPosition() {
    return this.position;
  }
  /**.
  *@return point
  */
  public Point getXyLocation() {
     return this.point;
  }
  /**.
  *@param r
  */
  public void connectRoom(Room r) {
    this.connectedRooms.add(r);
  }

  /**.
  *@return room, array list
  */
  public ArrayList<Room> getConnectedRooms() {
    return this.connectedRooms;
  }
  /**.
  *@return room
  *@param currentRoom
  */
  public Room getOtherRoom(Room currentRoom) {
    Room newRoom = new Room();
    int i = 0;
    while (i < this.connectedRooms.size()) {
        if (this.connectedRooms.get(i) != currentRoom) {
            newRoom = this.connectedRooms.get(i);
        }
        i++;
    }
    return newRoom;
  }
}

