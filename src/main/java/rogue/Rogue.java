package rogue;
import java.util.ArrayList;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class Rogue implements Serializable {

    private Player thePlayer;
    private ArrayList<Room> room = new ArrayList<Room>();
    private ArrayList<Item> item = new ArrayList<Item>();
    private ArrayList<Item> inventory = new ArrayList<Item>();

    private ArrayList<Food> foodArray = new ArrayList<Food>();
    private ArrayList<Potion> potionArray = new ArrayList<Potion>();
    private ArrayList<Ring> ringArray = new ArrayList<Ring>();
    private ArrayList<Clothing> clothesArray = new ArrayList<Clothing>();
    private ArrayList<Magic> magicArray = new ArrayList<Magic>();
    private ArrayList<SmallFood> smallFoodArray = new ArrayList<SmallFood>();

    private Player player;
    private ArrayList<Door> doors = new ArrayList<Door>();
    private HashMap<String, Character> symbols = new HashMap<String, Character>();

    public static final char UP = 'i';
    public static final char DOWN = 'k';
    public static final char RIGHT = 'l';
    public static final char LEFT = 'j';
    /**.
     *default
     */
    public Rogue() {
    }
    /**
     * @param theDungeonInfo
     * @throws ImpossiblePositionException
     */
    public Rogue(RogueParser theDungeonInfo) throws ImpossiblePositionException {
        this.thePlayer = new Player();
        int k = 0;

        String[] symbolArray = {"PASSAGE", "DOOR", "FLOOR", "PLAYER", "GOLD",
            "NS_WALL", "EW_WALL", "POTION", "SCROLL", "FOOD", "CLOTHING", "RING", "SMALLFOOD"};

        while (k < symbolArray.length) {
            this.symbols.put(symbolArray[k], theDungeonInfo.getSymbol(symbolArray[k]));
            k++;
        }
        Map<String, String> m = theDungeonInfo.nextRoom();
        Map<String, String> it = theDungeonInfo.nextItem();
        while (m != null) {
            addRoom(m, doors);
            m = theDungeonInfo.nextRoom();
        }
        ArrayList<Map<String, String>> newDoor = theDungeonInfo.getDoors();
        for (int i = 0; i < newDoor.size(); i++) {
            Map<String, String> d = newDoor.get(i);
            addDoor(d);
        }
        linkDoor();
        while (it != null) {
            addItem(it);
            it = theDungeonInfo.nextItem();
        }
    }
    /**
     * @param thePlayer2
     */
    public void setPlayer(Player thePlayer2) {
        this.thePlayer = thePlayer2;
    }
    /**
     * @param toAdd
     * @param drs
     */
    public void addRoom(Map<String, String> toAdd, ArrayList<Door> drs) {
        int i = 0;
        Room r = new Room();
        r.setHeight(Integer.parseInt(toAdd.get("height")));
        r.setWidth(Integer.parseInt(toAdd.get("width")));
        r.setId(Integer.parseInt(toAdd.get("id")));
        r.setBoolean(Boolean.parseBoolean(toAdd.get("start")));
        r.setSymbols(this.symbols);
        r.setDoor("N", Integer.parseInt(toAdd.get("N")));
        r.setDoor("S", Integer.parseInt(toAdd.get("S")));
        r.setDoor("E", Integer.parseInt(toAdd.get("E")));
        r.setDoor("W", Integer.parseInt(toAdd.get("W")));
        this.room.add(r);
    }
    /**
     * @param door
     */
    public void addDoor(Map<String, String> door) {
        Door d = new Door();
        d.setConRoom(Integer.parseInt(door.get("con_room")));
        d.setId(Integer.parseInt(door.get("curRoom").toString()));
        addDoor2(d);
        d.setDirection(door.get("dir").toString());
        d.setPosition(Integer.parseInt(door.get("wall_pos").toString()));
        this.doors.add(d);
    }
    /**.
     *@param d
     */
    public void addDoor2(Door d) {
        for (Room rm: this.room) {
            for (Room rm2: this.room) {
                if (rm.getId() == d.getId()) {
                    //System.out.println("room: " + rm.getId() + "door: " + d.getId());
                    d.setRoom(rm);
                    d.connectRoom(rm);
                }
                /*if (rm.getId() == d.getConRoom()) {
                    d.connectRoom(rm);
                }*/
                if (rm.getId() == rm2.getId() && rm2.getId() == d.getConRoom()) {
                    System.out.println("room id:" + rm.getId());
                    d.connectRoom(rm);
                }
            }
        }
    }
    /**.
     * nthing
     */
    public void linkDoor() {
        for (Room rm : this.room) {
            for (Door dr : this.doors) {
                if (rm.getId() == dr.getId()) {
                    rm.addDoor(dr);
                    rm.setDoor(dr.getDirection(), dr.getPosition());
                }
            }
        }
    }
    /**
     * @param toAdd
     * @throws ImpossiblePositionException
     */
    public void addItem(Map<String, String> toAdd) throws ImpossiblePositionException {
        Item i = new Item();
        i.setId(Integer.parseInt(toAdd.get("id")));
        i.setName(toAdd.get("name"));
        i.setType(toAdd.get("type"));
        i.setDescription(toAdd.get("description"));
        i.setDisplayCharacter(symbols.get(i.getType().toUpperCase()));
        if (toAdd.get("room") != null) {
            int rId = Integer.parseInt(toAdd.get("room"));
            for (Room rm : room) {
                if (rm.getId() == rId) {
                    i.setCurrentRoom(rm);
                    Point xy = new Point(Integer.parseInt(toAdd.get("x")), Integer.parseInt(toAdd.get("y")));
                    i.setXyLocation(xy);
                    rm.addItem(i);
                }
            }
        }
        this.item.add(i);
    }
    /**
     * @return String
     * @param input
     * @throws InvalidMoveException
     */
    public String makeMove(char input) throws InvalidMoveException {
        String messg = new String();
        Point pLoc = new Point((int) (this.thePlayer.getXyLocation().getX()),
        (int) (this.thePlayer.getXyLocation().getY()));
        if (input == UP) {
            this.thePlayer.getXyLocation().translate(0, -1);
        } else if (input == DOWN) {
            this.thePlayer.getXyLocation().translate(0, 1);
        } else if (input == RIGHT) {
            this.thePlayer.getXyLocation().translate(1, 0);
        } else if (input == LEFT) {
            this.thePlayer.getXyLocation().translate(-1, 0);
        }
        messg = makeMove2(pLoc);
        return messg;
    }
    /**
     * @return String
     * @param pLoc
     * @throws InvalidMoveException
     */
    public String makeMove2(Point pLoc) throws InvalidMoveException {
        String messg = new String();
        for (Room rm : this.room) {
            //this.thePlayer.setCurrentRoom(rm);
            if (rm.isPlayerInRoom()) {
                messg = makeMoveDoor(rm);
                if (this.thePlayer.getXyLocation().getX() < rm.getWidth() - 1
                    && this.thePlayer.getXyLocation().getX() > 0
                    && this.thePlayer.getXyLocation().getY() < rm.getHeight() - 1
                    && this.thePlayer.getXyLocation().getY() > 0) {
                    messg = makeMoveItems(rm);
                } else {
                    this.thePlayer.setXyLocation(pLoc);
                    messg = " ";
                    throw new InvalidMoveException("invalid");
                }
            }
        }
        return messg;
    }
    /**
     * @param rm
     * @return String
     */
    public String makeMoveDoor(Room rm) {
        int j = 0;
        String check = new String();
        ArrayList<Door> doorsArray = rm.getRoomDoors();
        while (j < doorsArray.size()) {
            if (this.thePlayer.getXyLocation().equals(doorsArray.get(j).getXyLocation())) {
                Room nRoom = doorsArray.get(j).getOtherRoom(rm);
                nRoom.setStart(true);
                nRoom.setPlayer(this.thePlayer);
                rm.setStart(false);
                rm.setPlayer(null);
                this.thePlayer.setCurrentRoom(nRoom);
                this.thePlayer.setXyLocation(new Point(1, 1));
                check = "door found";
            }
            j++;
        }
        return check;
    }
    /**
     * @param rm
     * @return String
     */
    public String makeMoveItems(Room rm) {
        String mssg = "moving";
        Item it = new Item();
        int removal = 0;
        ArrayList<Item> itemArray = rm.getRoomItems();
        for (int i = 0; i < itemArray.size(); i++) {
            if (this.thePlayer.getXyLocation().equals(itemArray.get(i).getXyLocation())) {
                mssg = "Picked up " + itemArray.get(i).getName();
                removal = 1;
                it = itemArray.get(i);
                this.inventory.add(it);
            }
            if (removal == 1) {
                makeMoveItems2(rm, it, itemArray);
            }
        }
        return mssg;
    }
    /**.
     * @param it
     * @param itemArray
     * @param rm
     */
    public void makeMoveItems2(Room rm, Item it, ArrayList<Item> itemArray) {
        seperateItemsByType(it);
        seperateItemsByType2(it);
        itemArray.remove(it);
        rm.setRoomItems(itemArray);
    }
    /**.
     * @param it
     */
    public void removeItems(Item it) {
        this.inventory.remove(it);
    }
    /**.
     * @return String
     */
    public String[] makeMoveInventory() {
        String[] inv = new String[this.inventory.size()];
        for (int i = 0; i < this.inventory.size(); i++) {
            inv[i] = this.inventory.get(i).getName();
        }
        return inv;
    }
    /**.
     * @param it
     */
    public void seperateItemsByType(Item it) {
        if (it.getType().equals("Food")) {
            Food food = new Food(it.getId(), it.getName(), it.getType(), it.getXyLocation());
            this.foodArray.add(food);
        }
        if (it.getType().equals("Potion")) {
            Potion potion = new Potion(it.getId(), it.getName(), it.getType(), it.getXyLocation());
            this.potionArray.add(potion);
        }
        if (it.getType().equals("Ring")) {
            Ring ring = new Ring(it.getId(), it.getName(), it.getType(), it.getXyLocation());
            this.ringArray.add(ring);
        }
    }
    /**.
     * @param it2
     */
    public void seperateItemsByType2(Item it2) {
        if (it2.getType().equals("Clothing")) {
            Clothing clothing = new Clothing(it2.getId(), it2.getName(), it2.getType(), it2.getXyLocation());
            this.clothesArray.add(clothing);
        }
        if (it2.getType().equals("Magic")) {
            Magic magic = new Magic(it2.getId(), it2.getName(), it2.getType(), it2.getXyLocation());
            this.magicArray.add(magic);
        }
        if (it2.getType().equals("SmallFood")) {
            SmallFood smallFood = new SmallFood(it2.getId(), it2.getName(), it2.getType(), it2.getXyLocation());
            this.smallFoodArray.add(smallFood);
        }
    }
    /**
     * @return string
     */
    public String getNextDisplay() {
        String str = new String();
        for (Room rm : this.room) {
            if (rm.isPlayerInRoom()) {
                this.thePlayer.setCurrentRoom(rm);
                rm.setPlayer(this.thePlayer);
                str += rm.displayRoom();
                //System.out.println(rm.getId());
            }
        }
        return str;
    }
    /**.
     *@throws ImpossiblePositionException
     *@param it
     */
    public void tossTheItem(Item it) throws ImpossiblePositionException {
        Point loc = new Point(this.thePlayer.getXyLocation());
        it.setXyLocation(loc);
        it.getXyLocation().translate(-1, 0);
        this.thePlayer.getCurrentRoom().addItem(it);
    }

    /**.
     * @return arrayList
     */
    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
    /**.
     * @return HashMap
     */
    public HashMap<String, Character> getSymbols() {
        return this.symbols;
    }

    /**
     * @return ArrayList
     */
    public ArrayList<Room> getRooms() {
        return this.room;

    }
    /**
     * @return arraayList
     */
    public ArrayList<Item> getItems() {
        return this.item;

    }
    /**
     * @return Player
     */
    public Player getPlayer() {
        return this.thePlayer;

    }
}

