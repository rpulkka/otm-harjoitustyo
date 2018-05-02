package yahtzee.domain;

import java.util.ArrayList;
import java.util.HashMap;

// @author rpulkka
/**
 * This class is meant to find all multiple instances of a value from the chosen
 * dice. The class thus aids some of the combination handlers, such as XOfAKind
 * and Pair to get their job done easier.
 */
public class DataList {

    private ArrayList<Die> dice;
    private HashMap<Integer, Integer> map;

    public DataList(ArrayList<Die> dice) {
        this.dice = dice;
        this.map = new HashMap<Integer, Integer>();
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 0);
        map.put(6, 0);
    }

    /**
     * Makes a list based on values that have x instances in the list of chosen
     * dice, x being the given parameter.
     *
     * @param howMany The searched number of instances.
     *
     * @return values List of values that show up in the original list of dice
     * as many times as the parameter howMany tells.
     */
    public ArrayList<Integer> list(int howMany) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (Die die : dice) {
            map.put(die.getValue(), map.get(die.getValue()) + 1);
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) == howMany) {
                values.add(i);
            }
        }
        return values;
    }
}
