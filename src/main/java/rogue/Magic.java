package rogue;
import java.awt.Point;
import java.io.Serializable;

public class Magic extends Item implements Serializable {
    private int id;
    private String name;
    private String type;
    private Point xyLocation;
    /**.
     *default
     */
    public Magic() {
        super();
    }

    /**.
     * @param id2
     * @param name2
     * @param type2
     * @param xyLocation2
     */
    public Magic(int id2, String name2, String type2, Point xyLocation2) {
        super(id2, name2, type2, xyLocation2);
    }
}

