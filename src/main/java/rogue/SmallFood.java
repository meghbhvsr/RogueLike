package rogue;
import java.awt.Point;
import java.io.Serializable;

public class SmallFood extends Food implements Tossable, Edible, Serializable {
    private int id;
    private String name;
    private String type;
    private Point xyLocation;
    /**.
     *default
     */
    public SmallFood() {
        super();
    }

    /**.
     * @param id2
     * @param name2
     * @param type2
     * @param xyLocation2
     */
    public SmallFood(int id2, String name2, String type2, Point xyLocation2) {
        super(id2, name2, type2, xyLocation2);
    }
    /**.
     * @return String
     */
    public String toss() {
        return this.name;
    }
    /**.
     *@return String
     */
    public String eat() {
        return this.name;
    }
}
