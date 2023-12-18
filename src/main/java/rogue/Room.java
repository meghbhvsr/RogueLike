package rogue;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;
import java.io.Serializable;


/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 */
public class Room implements Serializable {

  private int width;
  private int height;
  private int id;
  private ArrayList<Item> roomItems = new ArrayList<Item>();
  private HashMap<String, Character> symbols = new HashMap<String, Character>();
  private Player player;
  private HashMap<String, Integer> doorLocation = new HashMap<String, Integer>();
  private int northDoor = -1;
  private int southDoor = -1;
  private int eastDoor = -1;
  private int westDoor = -1;
  private String direction;
  private boolean start;
  private String name;
  private String symbol;
  private int roomId;
  private ArrayList<Door> doors = new ArrayList<Door>();
  private int conRoom;
    // Default constructor
  /**.
  *default
  */
  public Room() {
    this.width = 0;
    this.height = 0;
    this.id = 0;
    this.start = true;
    this.direction = new String();
  }
  /**
   *@param width2
   *@param height2
   *@param id2
   *@param start2
   *@param location2
   *@param direction2
   */
  public Room(int width2, int height2, int id2, boolean start2,  int location2, String direction2) {
    this.width = width;
    this.height = height;
    this.id = id;
    this.start = start;
    this.direction = direction;
  }

   // Required getter and setters below
  /**
   *@param symbol2
  */
  public void setSymbols(HashMap<String, Character> symbol2) {
    this.symbols = symbol2;
  }
  /**
   *@return int
  */
  public HashMap<String, Character> getSymbols() {
    return this.symbols;
  }
  /**
   *@return int
  */
  public int getWidth() {

    return this.width;
  }

  /**
   *@param newWidth
  */
  public void setWidth(int newWidth) {
    this.width = newWidth;
  }

  /**
   *@return int
   */
  public int getHeight() {

    return this.height;
  }
  /**
   *@param newHeight
  */
  public void setHeight(int newHeight) {
    this.height = newHeight;
  }
  /**
   *@return int
   */
  public int getId() {
    return this.id;
  }

  /**
   *@param newId
  */
  public void setId(int newId) {
    this.id = newId;
  }

  /**
   *@return ArrayList
   */
  public ArrayList<Item> getRoomItems() {
    return this.roomItems;
  }
  /**
   *@param newRoomItems
   */
  public void setRoomItems(ArrayList<Item> newRoomItems) {
    this.roomItems = roomItems;
  }
  /**
   *@return arrayList
   */
  public ArrayList<Door> getRoomDoors() {
    return this.doors;
  }
  /**
   *@return Player
   */
  public Player getPlayer() {
    return this.player;
  }
  /**
   *@param a
   */
  public void setStart(boolean a) {
    this.start = a;
  }
  /**
   *@param newPlayer
   */
  public void setPlayer(Player newPlayer) {
    this.player = newPlayer;
  }
  /**
   *@return int
   *@param direction2
   */
  public int getDoor(String direction2) {
    if (direction2.equals("N")) {
      return this.doorLocation.get("N");
    } else if (direction2.equals("S")) {
      return this.doorLocation.get("S");
    } else if (direction2.equals("E")) {
      return this.doorLocation.get("E");
    } else if (direction2.equals("W")) {
      return this.doorLocation.get("W");
    } else {
      return -1;
    }
  }
  /**
   *@param direction4
   *@param location2
   */
  public void setDoor(String direction4, int location2) {
    this.doorLocation.put(direction4, location2);
  }
  /**
   *@return string
   */
  public String getDirection() {
    return this.direction;
  }
  /**
   *@param direction3
   */
  public void setDirection(String direction3) {
    this.direction = direction3;
  }
/*
direction is one of NSEW
location is a number between 0 and the length of the wall
*/
  /**
   *@param newDoor
   */
  public void addDoor(Door newDoor) {
    this.doors.add(newDoor);
  }

  /**
   *@return boolean
   */
  public boolean isPlayerInRoom() {

    return start;
  }
  /**
   *@param start2
   */
  public void setBoolean(boolean start2) {
    this.start = start2;
  }
  /**
   *@param toAdd
   *@throws ImpossiblePositionException
   */
  public void addItem(Item toAdd) throws ImpossiblePositionException {
    try {
      if (toAdd.getXyLocation().getX() > getWidth() - 1 || toAdd.getXyLocation().getX() < 0
       || toAdd.getXyLocation().getY() > getHeight() - 1 || toAdd.getXyLocation().getY() < 0) {
        throw new ImpossiblePositionException("invalid");
      }
      this.roomItems.add(toAdd);
    } catch (ImpossiblePositionException e) {
      System.out.println(e + "positioning invalid");
      int randomNum1 = ThreadLocalRandom.current().nextInt(0, getWidth());
      int randomNum2 = ThreadLocalRandom.current().nextInt(0, getHeight());
      Point p = new Point(randomNum1, randomNum2);
      toAdd.setXyLocation(p);
      this.roomItems.add(toAdd);
    }
  }

   /**.
    * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents
    * @return (String) String representation of how the room looks
    */

  public String displayRoom() {
    String str = "";
    char[][] arr = new char[height][width];
    str += "Room:" + getId() + "\n";
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        displayRoomDoor(arr, i, j);
        for (int x = 0; x < this.roomItems.size(); x++) {
          if (this.roomItems.get(x).getXyLocation().getX() == j && this.roomItems.get(x).getXyLocation().getY() == i) {
            arr[i][j] = this.roomItems.get(x).getDisplayCharacter();
          }
        }
        str += arr[i][j];
      }
      str += '\n';
    }
    return str;
  }

/**
 *@param arr
 *@param i
 *@param j
 */
  public void displayRoomDoor(char[][] arr, int i, int j) {
    if (this.doorLocation.get("N") == j && i == 0) {
      arr[i][j] = this.symbols.get("DOOR");
    } else if (this.doorLocation.get("S") == j && i == height - 1) {
        arr[i][j] = this.symbols.get("DOOR");
    } else if (this.doorLocation.get("W") == i && j == 0) {
      arr[i][j] = this.symbols.get("DOOR");
    } else if (this.doorLocation.get("E") == i && j == width - 1) {
      arr[i][j] = this.symbols.get("DOOR");
    } else if (i == 0 || i == height - 1) {
      arr[i][j] = this.symbols.get("NS_WALL");
    } else if (j == 0 || j == width - 1) {
      arr[i][j] = this.symbols.get("EW_WALL");
    } else if (isPlayerInRoom() && i == this.player.getXyLocation().getY()
      && j == this.player.getXyLocation().getX()) {
      arr[i][j] = this.symbols.get("PLAYER");
    } else {
      arr[i][j] = this.symbols.get("FLOOR");
    }
  }
}



