package yahtzee.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// @author rpulkka
/**
 * This class is meant to find all multiple instances of a value from the chosen
 * dice. The class thus aids XOfAKind to get its job done easier.
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
     * Makes a list based on values that have x instances in the list of chosen
     * dice, x being the given parameter.
     *
     * @param howMany The searched number of instances.
     *
     * @return values List of values that show up in the original list of dice
     * as many times as the parameter tells.
     */
    public ArrayList<Integer> getList(int howMany) {
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

    /**
     * Makes a list based on values that have more than x instances in the list
     * of chosen dice, x being the given parameter.
     *
     * @param lowerLimit Lowest amount of instances accepted to the list.
     *
     * @return values List of values that show up in the original list of dice
     * more times as the parameter tells.
     */
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
