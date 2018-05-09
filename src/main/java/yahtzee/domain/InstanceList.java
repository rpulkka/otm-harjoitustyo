package yahtzee.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// @author rpulkka
/**
 * This class is meant to find all multiple instances of a value from the chosen
 * dice. The class thus aids some of the combination handlers, such as XOfAKind
 * and Pair to get their job done easier.
 */
public class InstanceList {

    private ArrayList<Die> dice;
    private HashMap<Integer, Integer> map;

    public InstanceList(ArrayList<Die> dice) {
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
     * Makes a list based on instances of values in the list of chosen dice.
     *
     * @return values List of values by their instances in chosen dice.
     */
    public ArrayList<Integer> getList(int howMany) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (Die die : dice) {
            map.put(die.getValue(), map.get(die.getValue()) + 1);
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) == howMany || howMany!=-1) {
                values.add(i);
            }
        }
        return values;
    }
    
    public ArrayList<Integer> limitedList(int lowerLimit) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (Die die : dice) {
            map.put(die.getValue(), map.get(die.getValue()) + 1);
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) >= lowerLimit) {
                values.add(i);
            }
        }
        Collections.sort(values, Collections.reverseOrder());
        return values;
    }
}
